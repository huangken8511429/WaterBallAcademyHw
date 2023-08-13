package action.Skill;

import Base.Role;

import java.util.List;

public class Poison extends Skill {
    @Override
    protected void setName() {
        this.name = "下毒";
    }

    @Override
    protected void setMp() {
        this.mp = 80;
    }

    @Override
    protected void setTargetNumber() {
        this.targetNumber = 1;
    }

    @Override
    protected void setTargetType() {
        targetType = TargetType.ENEMY;
    }

    @Override
    public void doPerform(Role role, List<Role> targets) {
        for (Role target : targets) {
            target.setState(new State.Poisoned(target));
        }
    }
}
