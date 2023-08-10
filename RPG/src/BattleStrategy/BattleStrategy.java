public interface BattleStrategy {
    Action selectAction(Role role);

    Role[] selectTarget(Role role);
}
