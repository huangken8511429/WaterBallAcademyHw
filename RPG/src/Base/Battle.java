package Base;

import action.Action;

import java.util.List;
import java.util.stream.Collectors;

import static Util.util.printf;

public class Battle {
    private Troop allyTroop;
    private Troop enemyTroop;
    private int round = 0;

    public Battle(Troop allyTroop, Troop enemyTroop) {
        this.allyTroop = allyTroop;
        this.enemyTroop = enemyTroop;
        allyTroop.setBattle(this);
        enemyTroop.setBattle(this);
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
            if (!role.state.canAction()) {
                return;
            }
            Action action = askRoleChooseAction(role);
            List<Role> targets = askAllysChooseTarget(role, action);
            role.setMp(role.getMp() - action.getMp());
            role.state.handleAction(action);
            action.perform(role, targets);
            role.state.checkExpiredState(role.getActions());
        }
    }

    private List<Role> getOtherAliveAllys() {
        return allyTroop.getRoles().stream().filter(Role::isAlive).filter(role -> !(role instanceof Hero)).collect(Collectors.toList());
    }

    private List<Role> getAliveAllys() {
        return allyTroop.getRoles().stream().filter(Role::isAlive).collect(Collectors.toList());
    }

    private List<Role> getAliveAllys(Role role) {
        return allyTroop.getRoles().stream().filter(ally -> !ally.equals(role)).collect(Collectors.toList());
    }

    private List<Role> getAliveEnemies() {
        return enemyTroop.getRoles().stream().filter(Role::isAlive).collect(Collectors.toList());
    }

    private Action askRoleChooseAction(Role role) {
        return role.chooseAction();
    }

    private List<Role> askAllysChooseTarget(Role role, Action action) {
        List<Role> targets = null;
        Action.TargetType targetType = action.getTargetType();
        if (targetType == Action.TargetType.AllY) {
            if (role.getTroop().equals(allyTroop)) {
                targets = role.chooseTargets(action, getAliveAllys(role));
            } else {
                targets = role.chooseTargets(action, getAliveEnemies());
            }
        } else if (targetType == Action.TargetType.ENEMY) {
            if (role.getTroop().equals(allyTroop)) {
                targets = role.chooseTargets(action, getAliveEnemies());
            } else {
                targets = role.chooseTargets(action, getAliveAllys());
            }
        } else if (targetType == Action.TargetType.SELF) {
            targets = role.chooseTargets(action, List.of(role));
        } else if (targetType == Action.TargetType.ALL) {
            targets = getAllAliveRoles(role);
        }
        return targets;
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

    private List<Role> getAllAliveRoles(Role role) {
        List<Role> aliveAllys = getAliveAllys();
        List<Role> aliveEnemies = getAliveEnemies();
        aliveAllys.addAll(aliveEnemies);
        aliveAllys.remove(role);
        return aliveAllys;
    }
}

