package action.Skill;

import Base.Role;

import java.util.List;

public class Curse extends Skill {
    @Override
    protected void setName() {
        this.name = "詛咒";
    }

    @Override
    protected void setMp() {
        this.mp = 100;
    }

    @Override
    protected void setTargetNumber() {
        this.targetNumber = 1;
    }

    @Override
    protected void setTargetType() {
        this.targetType = TargetType.ENEMY;
    }

    @Override
    public void doPerform(Role role, List<Role> targets) {
        for (Role target : targets) {
            target.addMonitor(role);
        }
    }
}
