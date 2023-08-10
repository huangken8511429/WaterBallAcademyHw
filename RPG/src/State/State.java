package Base;

public abstract class State {
    protected String name;
    protected int effectRound = 3;
    protected Role role;

    public State(String name, int effectRound, Role role) {
        this.name = name;
        this.effectRound = effectRound;
        this.role = role;
    }

    public void exitState() {
        role.setState(new Normal("正常",Integer.MAX_VALUE,))
    }

    public abstract void roundStart();

    private boolean isExpired() {
        return effectRound < 1;
    }
}
