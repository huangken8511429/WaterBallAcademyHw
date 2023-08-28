package action.OnePuchHandler;

import Base.Role;

import java.util.List;

public abstract class OnePunchHandler {
    private final OnePunchHandler next;

    public OnePunchHandler(OnePunchHandler next) {
        this.next = next;
    }

    public void handle(Role attacker, List<Role> targets) {
        if (match(targets)) {
            doHandle(attacker, targets);
        } else if (next != null) {
            next.handle(attacker, targets);
        }
    }

    protected abstract boolean match(List<Role> targets);

    protected abstract void doHandle(Role attacker, List<Role> targets);

}
