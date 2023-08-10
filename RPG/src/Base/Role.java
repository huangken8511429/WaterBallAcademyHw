import java.util.List;

public class Role {
    private BattleStrategy battleStrategy;
    private int hp;
    private int mp;
    private int str;
    private String name;
    private List<Action> actions ;

    public boolean isDead() {
        return hp < 0;
    }

    public boolean isHero() {
        return this instanceof Hero;
    }

    public void action() {
        Action action = battleStrategy.selectAction(this);
        Role[] targets = battleStrategy.selectTarget(this);
        action.perform(this, targets);
    }
}
