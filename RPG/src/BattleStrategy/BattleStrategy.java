package BattleStrategy;
import action.Action;
import Base.Role;

import java.util.List;

public interface BattleStrategy {
    Action selectAction(Role role);
    List<Role> selectTarget(Action action, List<Role> enemies);
}
