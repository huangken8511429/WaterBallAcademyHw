package State;

import action.Action;
import Base.Role;

import java.util.List;

public abstract class State {
    protected String name;
    protected int effectRound = 3;
    protected Role role;

    public State(Role role) {
        setName();
        this.role = role;
    }

    protected abstract void setName();

    public void exitState() {
        role.setState(new Normal(Integer.MAX_VALUE, role));
    }

    public void roundStart() {
        decreaseRound();
    }

    protected boolean isExpired() {
        return effectRound <= 0;
    }

    protected void decreaseRound() {
        effectRound--;
    }

    public void checkExpiredState(List<Action> actions) {
        if (isExpired()) {
            this.exitState();
        }
    }

    public boolean canAction() {
        return true;
    }

    public void handleAction(Action action) {
    }

    public String getName() {
        return name;
    }
}
