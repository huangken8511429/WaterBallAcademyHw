package action;

import Base.Role;

import java.util.List;

public class BasicAttack extends Action {

    @Override
    protected void setName() {
        this.name = "普通攻擊";
    }

    @Override
    protected void setMp() {
        this.mp = 0;
    }

    @Override
    protected void setTargetNumber() {
        this.targetNumber = 1;
    }

    @Override
    public void setDamage(int str) {
        this.damage = str;
    }

    @Override
    protected void setTargetType() {
        this.targetType = TargetType.ENEMY;
    }

    @Override
    public void printInformation(Role role, List<Role> targets) {
        StringBuilder targetName = new StringBuilder();
        for (Role target : targets) {
            targetName.append(target.getName());
        }
        System.out.printf("%s 攻擊 %s。\n", role.getName(), targetName.toString());
    }

    @Override
    public void doPerform(Role role, List<Role> targets) {
        for (Role target : targets) {
            target.damage(role, damage);
        }
    }
}
