package action.Skill;

import Base.Role;
import action.Action;

import java.util.List;

public class CheerUp extends Skill {
    @Override
    protected void setName() {
        this.name = "鼓舞";
    }

    @Override
    protected void setMp() {
        this.mp = 100;
    }

    @Override
    protected void setTargetNumber() {
        this.targetNumber = 3;
    }

    @Override
    protected void setTargetType() {
        this.targetType = TargetType.AllY;
    }

    @Override
    public void doPerform(Role role, List<Role> targets) {
        for (Role target : targets) {
            target.enterState(new State.CheerUp(target));
        }
    }
}
