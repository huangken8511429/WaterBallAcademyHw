package Base;

import java.util.List;

public class RPG {
    private final List<Troop> troops;
    private final Battle battle;

    public RPG(List<Troop> troops, Battle battle) {
        this.troops = troops;
        this.battle = battle;
    }

    public void start() {
        battle.startBattle();
    }
}
