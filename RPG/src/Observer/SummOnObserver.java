package Observer;

import Base.Role;

import java.util.List;

public class SummonOnObserver implements SkillObserver {


    @Override
    public void update(List<Role> monitor, Role effectRole) {
        monitor.forEach(role -> role.gainHp(30));
    }
}
