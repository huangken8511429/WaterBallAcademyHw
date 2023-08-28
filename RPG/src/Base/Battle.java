package Base;

import action.Action;
import action.SkillTargetTypeHandler.SkillTargetTypeHandler;

import java.util.List;
import java.util.stream.Collectors;

import static Util.util.printf;

public class Battle {
    private final Troop allyTroop;
    private final Troop enemyTroop;
    private final SkillTargetTypeHandler skillTargetTypeHandler;

    public Battle(Troop allyTroop, Troop enemyTroop, SkillTargetTypeHandler skillTargetTypeHandler) {
        this.allyTroop = allyTroop;
        this.enemyTroop = enemyTroop;
        allyTroop.setBattle(this);
        enemyTroop.setBattle(this);
        this.skillTargetTypeHandler = skillTargetTypeHandler;
    }

    public void startBattle() {
        addPrefix();
        while (!isGameOver()) {
            troopTakeTurn(getHero());
            troopTakeTurn(getOtherAliveAllys());
            troopTakeTurn(getAliveEnemies());
        }
        showWinner();
    }

    private List<Role> getHero() {
        return allyTroop.getRoles().stream().filter(Role::isHero).collect(Collectors.toList());
    }

    private void addPrefix() {
        for (Role role : getAliveAllys()) {
            role.setName("[1]" + role.getName());
        }
        for (Role role : getAliveEnemies()) {
            role.setName("[2]" + role.getName());
        }
    }

    private boolean isGameOver() {
        return enemyTroop.annihilate() || allyTroop.heroIsDead();
    }

    private void troopTakeTurn(List<Role> roles) {
        for (Role role : roles) {
            if (enemyTroop.annihilate()) {
                return;
            }
            printf("輪到 %s (HP: %d, MP: %d, STR: %d, State: %s)。\n",
                    role.getName(), role.getHp(), role.getMp(), role.getStr(), role.state.getName());
            role.state.roundStart();
            if (role.state.canAction() && role.isAlive()) {
                Action action = askRoleChooseAction(role);
                List<Role> targets = askAllysChooseTarget(role, action);
                role.setMp(role.getMp() - action.getMp());
                role.state.handleAction(action);
                action.perform(role, targets);
            }
            role.state.checkExpiredState(role.getActions());
        }
    }

    private List<Role> getOtherAliveAllys() {
        return allyTroop.getRoles().stream().filter(Role::isAlive).filter(role -> !(role instanceof Hero)).collect(Collectors.toList());
    }

    public List<Role> getAliveAllys() {
        return allyTroop.getRoles().stream().filter(Role::isAlive).collect(Collectors.toList());
    }

    public List<Role> getAliveAllys(Role role) {
        return allyTroop.getRoles().stream().filter(Role::isAlive).filter(ally -> !ally.equals(role)).collect(Collectors.toList());
    }

    public List<Role> getAliveEnemies() {
        return enemyTroop.getRoles().stream().filter(Role::isAlive).collect(Collectors.toList());
    }

    public List<Role> getAliveEnemies(Role role) {
        return enemyTroop.getRoles().stream().filter(Role::isAlive).filter(enemy -> !enemy.equals(role)).collect(Collectors.toList());
    }

    private Action askRoleChooseAction(Role role) {
        return role.chooseAction();
    }

    private List<Role> askAllysChooseTarget(Role role, Action action) {
        return skillTargetTypeHandler.handle(action, role, this);
    }


    private List<Role> askEnemiesChooseTarget(Role role, Action action) {
        List<Role> targets = null;
        Action.TargetType targetType = action.getTargetType();
        if (targetType == Action.TargetType.AllY) {
            targets = role.chooseTargets(action, getAliveEnemies());
        } else if (targetType == Action.TargetType.ENEMY) {
            targets = role.chooseTargets(action, getAliveAllys());
        } else if (targetType == Action.TargetType.SELF) {
            targets = role.chooseTargets(action, List.of(role));
        }
        return targets;
    }

    private void showWinner() {
        if (allyTroop.heroIsDead()) {
            System.out.println("你失敗了！");
        } else {
            System.out.println("你獲勝了！");
        }
    }

    public List<Role> getEnemies() {
        return enemyTroop.getRoles();
    }

    public List<Role> getAllys() {
        return allyTroop.getRoles();
    }

    public Troop getAllyTroop() {
        return allyTroop;
    }

    public List<Role> getAllAliveRoles(Role role) {
        List<Role> aliveAllys = getAliveAllys();
        List<Role> aliveEnemies = getAliveEnemies();
        aliveAllys.addAll(aliveEnemies);
        aliveAllys.remove(role);
        return aliveAllys;
    }
}

