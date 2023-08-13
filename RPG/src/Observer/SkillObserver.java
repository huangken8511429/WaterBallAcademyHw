package Observer;

import Base.Role;

import java.util.List;

public interface SkillObserver {
    void update(List<Role> monitors, Role role);
}
