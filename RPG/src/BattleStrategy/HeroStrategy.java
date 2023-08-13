package BattleStrategy;

import action.Action;
import Base.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Util.util.printActionName;


public class HeroStrategy implements BattleStrategy {
    @Override
    public Action selectAction(Role role) {
        printActionName(role);
        Scanner scanner = new Scanner(System.in);
        return role.getActions().get(scanner.nextInt());
    }

    @Override
    public List<Role> selectTarget(Action action, List<Role> enemies) {
        if (action.getTargetNumber() >= enemies.size() || action.getTargetType() == Action.TargetType.SELF) {
            return enemies;
        } else {
            StringBuilder enemyInformation = new StringBuilder();
            for (Role role : enemies) {
                int index = enemies.indexOf(role);
                enemyInformation.append("(")
                        .append(index)
                        .append(")")
                        .append(" ")
                        .append(role.getName())
                        .append(" ");
            }
            System.out.printf("選擇 %d 位目標: %s", action.getTargetNumber(), enemyInformation);
            Scanner scanner = new Scanner(System.in);
            List<Role> targets = new ArrayList<>();
            for (int i = 0; i < action.getTargetNumber(); i++) {
                targets.add(enemies.get(scanner.nextInt()));
            }
            return targets;
        }
    }
}
