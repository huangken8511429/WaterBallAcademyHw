package action.Skill;

import Base.Role;
import action.Action;

import java.util.List;

public class CheerUp extends Skill {

    public CheerUp() {
        super("鼓舞", 100, 3, 0, TargetType.AllY);
    }

    @Override
    public void doPerform(Role role, List<Role> targets) {
        for (Role target : targets) {
            target.enterState(new State.CheerUp(target));
        }
    }
}
