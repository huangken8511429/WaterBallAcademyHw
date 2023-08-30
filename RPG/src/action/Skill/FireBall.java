package action.Skill;

import Base.Role;

import java.util.List;

public class FireBall extends Skill {

    public FireBall() {
        super("火球", 50, Integer.MAX_VALUE, 50, TargetType.ENEMY);
    }

    @Override
    public void doPerform(Role role, List<Role> targets) {
       for (Role target : targets) {
           target.damage(role,getDamage());
       }
    }
}
