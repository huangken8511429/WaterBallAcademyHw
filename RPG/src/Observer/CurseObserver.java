package Observer;

import Base.Role;

import java.util.List;

public class CurseObserver implements SkillObserver {
    @Override
    public void update(List<Role> monitors, Role role) {
        monitors.forEach(monitor -> monitor.gainHp(role.getMp()));
    }
}
