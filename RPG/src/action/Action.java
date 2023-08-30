package action;

import Base.Role;

import java.util.List;

public abstract class Action {
    protected final  String name;
    protected final int mp;
    protected int damage;
    protected Role role;
    protected final int targetNumber;
    protected final TargetType targetType;

    protected Action(String name, int mp, int damage, int targetNumber, TargetType targetType) {
        this.name = name;
        this.mp = mp;
        this.damage = damage;
        this.targetNumber = targetNumber;
        this.targetType = targetType;
    }

    public void perform(Role role, List<Role> targets) {
        printInformation(role, targets);
        doPerform(role, targets);
    }

    public void printInformation(Role role, List<Role> targets) {
        StringBuilder targetName = new StringBuilder();
        List<String> tempTargetNames = targets.stream().map(Role::getName).toList();
        for (String s : tempTargetNames) {
            if (tempTargetNames.indexOf(s) == tempTargetNames.size() - 1) {
                targetName.append(s);
            } else {
                targetName.append(s).append(", ");
            }
        }
        if (targetName.length() == 0) {
            System.out.printf("%s 使用了 %s。\n", role.getName(), this.name);
        } else {
            System.out.printf("%s 對 %s 使用了 %s。\n", role.getName(), targetName.toString(), this.name);
        }
    }

    public abstract void doPerform(Role role, List<Role> targets);

    public boolean isMpEnough(Role role) {
        return role.getMp() >= mp;
    }

    public int getDamage() {
        return damage;
    }

    public int getTargetNumber() {
        return targetNumber;
    }

    public TargetType getTargetType() {
        return targetType;
    }

    public void increaseDamage(int value) {
        damage += value;
    }

    public void decreaseDamage(int value) {
        damage -= value;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public int getMp() {
        return mp;
    }

    public enum TargetType {
        AllY, ENEMY, SELF, ALL
    }

    public Role getRole() {
        return role;
    }
}
