package action.Skill;

import Base.Role;
import Base.Slime;
import Base.Troop;
import BattleStrategy.AIStrategy;
import Observer.SummonObserver;

import java.util.ArrayList;
import java.util.List;

public class Summon extends Skill {
    @Override
    protected void setName() {
        this.name = "召喚";
    }

    @Override
    protected void setMp() {
        this.mp = 150;
    }

    @Override
    protected void setTargetNumber() {
        this.targetNumber = 0;
    }

    @Override
    protected void setTargetType() {
        targetType = TargetType.SELF;
    }

    @Override
    public void doPerform(Role role, List<Role> targets) {
        Slime slime = null;
        Troop troop = role.getTroop();
        if (troop.isAllyTroop()) {
            slime = new Slime(100, 0, 50, "[1]Slime", new ArrayList<>(), new AIStrategy());
        } else {
            slime = new Slime(100, 0, 50, "[2]Slime", new ArrayList<>(), new AIStrategy());
        }
        slime.setTroop(troop);
        slime.addMonitor(role);
        slime.register(new SummonObserver());
        troop.addRole(slime);
    }

    @Override
    public void printInformation(Role role, List<Role> targets) {
        System.out.printf("%s 使用了 %s。\n", role.getName(), this.name);
    }
}
