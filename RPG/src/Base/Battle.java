import java.util.List;

public class Battle {
    private Troop allyTroop;
    private Troop enemyTroop;
    private int round;

    private List<Role> getAliveAllys() {
        return allyTroop.getRoles();
    }

    private List<Role> getAliveEnemies() {
        return enemyTroop.getRoles();
    }

    private void startBattle() {
        while (!isGameOver()) {
            for (Role role : getAliveAllys()) {
                role.action();
            }
            for (Role role : getAliveEnemies()) {
                role.action();
            }
        }
        showWinner();
    }

    private boolean isGameOver() {
        return enemyTroop.annihilate() || allyTroop.heroIsDead();
    }

    private void showWinner() {
        if (enemyTroop.annihilate()) {
            System.out.println("你獲勝了！");
        } else {
            System.out.println("你失敗了！");
        }
    }
}
