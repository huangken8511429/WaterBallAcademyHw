package action;

import Base.Role;

import java.time.temporal.IsoFields;
import java.util.List;

public abstract class Action {
    protected String name;
    protected int mp;
    protected int damage;
    protected Role role;
    protected int targetNumber;
    protected TargetType targetType;

    public Action() {
        setName();
        setMp();
        setTargetNumber();
        setTargetType();
    }

    protected abstract void setName();

    protected abstract void setMp();

    protected abstract void setTargetNumber();

    protected void setDamage(int str) {

    }

    protected abstract void setTargetType();


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
