package action.Skill;

import Base.Role;

import java.util.List;

import static action.Action.TargetType.ENEMY;

public class WaterBall extends Skill {

    public WaterBall() {
        super("水球", 50, 1, 120, ENEMY);
    }

    @Override
    public void doPerform(Role role, List<Role> targets) {
        for (Role target : targets) {
            target.damage(role, getDamage());
        }
    }
}
