package action.Skill;

import action.Action;

public abstract class Skill extends Action {
    public Skill() {
        setName();
        setMp();
        setTargetNumber();
        setDamage();
        setTargetType();
    }
    protected void setDamage(){

    }


}
