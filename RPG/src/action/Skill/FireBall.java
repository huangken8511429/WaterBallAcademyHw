package action.Skill;

import Base.Role;

import java.util.List;

public class FireBall extends Skill {

    @Override
    protected void setName() {
        this.name = "火球";
    }

    @Override
    protected void setMp() {
        this.mp = 50;
    }

    @Override
    protected void setTargetNumber() {
        this.targetNumber = Integer.MAX_VALUE;
    }

    @Override
    protected void setDamage() {
        this.damage = 50;
    }

    @Override
    protected void setTargetType() {
        this.targetType = TargetType.ENEMY;
    }

    @Override
    public void doPerform(Role role, List<Role> targets) {
       for (Role target : targets) {
           target.damage(role,damage);
       }
    }
}
