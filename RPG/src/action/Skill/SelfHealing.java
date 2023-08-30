package action.Skill;

import Base.Role;

import java.util.List;

public class SelfHealing extends Skill {
    public SelfHealing() {
        super("自我治療", 50, 1, 0, TargetType.SELF);
    }

    @Override
    public void doPerform(Role role, List<Role> targets) {
        role.gainHp(150);
    }

    @Override
    public void printInformation(Role role, List<Role> targets) {
        System.out.printf("%s 使用了 %s\n", role.getName(), this.getName());
    }
}
