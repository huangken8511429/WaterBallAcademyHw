package Base;

import action.Action;
import BattleStrategy.BattleStrategy;
import Observer.SkillObserver;
import State.*;
import action.BasicAttack;

import java.util.ArrayList;
import java.util.List;

public class Role {
    private int hp;
    private int mp;
    private int str;

    protected String name;
    protected List<Role> monitors;
    protected List<Action> actions = new ArrayList<>();
    protected State state = new Normal(Integer.MAX_VALUE, this);
    protected BattleStrategy battleStrategy;
    protected List<SkillObserver> observers = new ArrayList<>();
    protected Troop troop;

    public Role(int hp, int mp, int str, String name, List<Action> actions, BattleStrategy battleStrategy) {
        this.hp = hp;
        this.mp = mp;
        this.str = str;
        addBasicAttack(str);
        this.actions.addAll(actions);
        this.name = name;
        this.battleStrategy = battleStrategy;
        for (Action action : actions) {
            action.setRole(this);
        }
        monitors = new ArrayList<>();
    }

    private void addBasicAttack(int str) {
        BasicAttack basicAttack = new BasicAttack();
        basicAttack.setDamage(str);
        this.actions.add(basicAttack);
    }

    public Action chooseAction() {
        Action action = battleStrategy.selectAction(this);
        if (action.isMpEnough(this)) {
            return action;
        } else {
            System.out.print("你缺乏 MP，不能進行此行動。\n");
            return chooseAction();
        }
    }

    public List<Role> chooseTargets(Action action, List<Role> targets) {
        return battleStrategy.selectTarget(action, targets);
    }

    private boolean canAction() {
        return state.canAction();
    }

    public void damage(Role attacker, int value) {
        System.out.printf("%s 對 %s 造成 %d 點傷害\n", attacker.getName(), this.getName(), value);
        this.hp -= value;
        if (isDead()) {
            System.out.printf("%s 死亡。\n", this.getName());
            this.afterDead();
        }
    }

    public void stateDamage(int value) {
        this.hp -= value;
        if (isDead()) {
            System.out.printf("%s 死亡。\n", this.getName());
            this.afterDead();
        }
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

    public void gainHp(int value) {
        this.hp += value;
    }

    public void enterState(State state) {
        this.state.exitState();
        this.state = state;
//        state.roundStart();
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getMp() {
        return mp;
    }

    public boolean isDead() {
        return hp <= 0;
    }

    public void afterDead() {
        for (SkillObserver skillObserver : observers) {
            skillObserver.update(monitors, this);
        }
    }

    public boolean isHero() {
        return this instanceof Hero;
    }

    public void register(SkillObserver skillObserver) {
        observers.add(skillObserver);
    }

    private void unregister(SkillObserver skillObserver) {
        observers.remove(skillObserver);
    }

    public int getStr() {
        return str;
    }

    public List<Action> getActions() {
        return actions;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMp(int value) {
        this.mp = value;
    }

    public Troop getTroop() {
        return troop;
    }

    public void addMonitor(Role target) {
        monitors.add(target);
    }

    public void setTroop(Troop troop) {
        this.troop = troop;
    }
}
