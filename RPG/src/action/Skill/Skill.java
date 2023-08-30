package action.Skill;

import action.Action;

public abstract class Skill extends Action {
    public Skill(String name, int mp, int targetNumber, int damage, TargetType targetType) {
        super(name, mp, damage, targetNumber, targetType);
    }

    @Override
    public String getName() {
        return name;
    }
}
