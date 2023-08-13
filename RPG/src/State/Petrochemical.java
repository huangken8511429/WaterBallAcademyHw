package State;

import Base.Role;

public class Petrochemical extends State {
    public Petrochemical(Role role) {
        super(role);
    }

    @Override
    protected void setName() {
        this.name = "石化";
    }

    @Override
    public boolean canAction() {
        return false;
    }
}
