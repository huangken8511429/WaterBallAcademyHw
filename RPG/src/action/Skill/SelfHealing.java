package action.Skill;

import Base.Role;

import java.util.List;

public class SelfHealing extends Skill {
    @Override
    protected void setName() {
        this.name = "自我治療";
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
    protected void setTargetType() {
        targetType = TargetType.SELF;
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
