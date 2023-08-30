package action;

import Base.Role;

import java.util.List;

public class BasicAttack extends Action {

    public BasicAttack(int str) {
        super("普通攻擊", 0, 0, 1, TargetType.ENEMY);
        this.damage = str;
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
