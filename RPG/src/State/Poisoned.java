package State;

import Base.Role;

public class Poisoned extends State {
    public Poisoned(Role role) {
        super(role);
    }

    @Override
    protected void setName() {
        this.name = "中毒";
    }


    @Override
    public void roundStart() {
        role.stateDamage(30);
        decreaseRound();
    }
}
