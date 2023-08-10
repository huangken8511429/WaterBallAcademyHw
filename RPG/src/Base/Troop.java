import java.util.List;

public class Troop {
    private final List<Role> roles;

    public Troop(List<Role> roles) {
        this.roles = roles;
    }

    public boolean annihilate() {
        return roles.stream().allMatch(Role::isDead);
    }

    public List<Role> getRoles() {
        return roles;
    }

    public boolean heroIsDead() {
        return roles.stream().filter(Role::isHero).findFirst().orElseThrow().isDead();
    }
}
