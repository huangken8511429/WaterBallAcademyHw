package State;

import Base.Role;

public class Normal extends State {
    private final int effectRound = Integer.MAX_VALUE;

    public Normal(int effectRound, Role role) {
        super(role);
    }

    @Override
    protected void setName() {
        this.name = "正常";
    }
}
