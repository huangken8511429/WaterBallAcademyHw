package action.Skill;

import Base.Role;

import java.util.List;

import static action.Action.TargetType.ENEMY;

public class WaterBall extends Skill {

    @Override
    protected void setName() {
        this.name = "水球";
    }

    @Override
    protected void setMp() {
        this.mp = 50;
    }

    @Override
    protected void setTargetNumber() {
        this.targetNumber = 1;
    }

    @Override
    protected void setDamage() {
        this.damage = 120;
    }

    @Override
    protected void setTargetType() {
        this.targetType = ENEMY;
    }

    @Override
    public void doPerform(Role role, List<Role> targets) {
        for (Role target : targets) {
            target.damage(role, damage);
        }
    }
}
