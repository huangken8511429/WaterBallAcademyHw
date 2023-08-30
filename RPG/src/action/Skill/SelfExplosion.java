package action.Skill;

import Base.Role;

import java.util.List;

public class SelfExplosion extends Skill {
    public SelfExplosion() {
        super("自爆", 200, Integer.MAX_VALUE, 0, TargetType.ALL);
    }

    @Override
    public void doPerform(Role role, List<Role> targets) {
        for (Role target : targets) {
            target.damage(role, 150);
        }
        role.stateDamage(Integer.MAX_VALUE);
    }
}
