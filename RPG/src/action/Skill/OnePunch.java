package action.Skill;

import Base.Role;
import action.OnePuchHandler.OnePunchHandler;

import java.util.List;

public class OnePunch extends Skill {
    private final OnePunchHandler onePunchHandler;

    public OnePunch(OnePunchHandler onePunchHandler) {
        this.onePunchHandler = onePunchHandler;
    }

    @Override
    protected void setName() {
        this.name = "一拳攻擊";
    }

    @Override
    protected void setMp() {
        this.mp = 180;
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
        onePunchHandler.handle(role, targets);
    }
}
