package State;

import action.Action;
import Base.Role;

import java.util.List;

public class CheerUp extends State {
    private boolean hasIncreaseDamage = false;

    public CheerUp(Role role) {
        super(role);
    }

    @Override
    protected void setName() {
        this.name = "受到鼓舞";
    }

    @Override
    public void handleAction(Action action) {
        if (!hasIncreaseDamage) {
            action.increaseDamage(50);
            hasIncreaseDamage = true;
        }
    }

    @Override
    public void exitState() {
        for (Action action : role.getActions()) {
            action.decreaseDamage(50);
        }
    }
}
