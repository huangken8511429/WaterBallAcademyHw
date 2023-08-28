package Base;

import action.SkillTargetTypeHandler.SkillTargetTypeHandler;

import java.util.ArrayList;
import java.util.List;

public class Troop {
    private Battle battle;
    private List<Role> roles = new ArrayList<>();

    public Troop(List<Role> roles) {
        this.roles.addAll(roles);
        this.roles.forEach(role -> role.setTroop(this));
    }

    public boolean annihilate() {
        return roles.stream().filter(Role::isAlive).toList().size() == 0;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public boolean heroIsDead() {
        return roles.stream().filter(Role::isHero).findFirst().orElseThrow().isDead();
    }

    public List<Role> getEnemies() {
        return battle.getEnemies();
    }

    public List<Role> getAllys() {
        return battle.getAllys();
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public boolean isAllyTroop() {
        return this.equals(battle.getAllyTroop());
    }
}
