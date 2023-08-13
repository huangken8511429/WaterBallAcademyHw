package action.Skill;

import Base.Role;

import java.util.List;

public class SelfExplosion extends Skill {
    @Override
    protected void setName() {
        this.name = "自爆";
    }

    @Override
    protected void setMp() {
        this.mp = 200;
    }

    @Override
    protected void setTargetNumber() {
        this.targetNumber = Integer.MAX_VALUE;
    }

    @Override
    protected void setTargetType() {
        this.targetType = TargetType.ALL;
    }

    @Override
    public void doPerform(Role role, List<Role> targets) {
        for (Role target : targets) {
            target.damage(role, 150);
        }
        role.stateDamage(Integer.MAX_VALUE);
    }
}
