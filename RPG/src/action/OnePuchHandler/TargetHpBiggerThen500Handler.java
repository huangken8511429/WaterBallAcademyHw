package action.OnePuchHandler;

import Base.Role;

public class TargetHpBiggerThen300Handler extends OnePunchHandler{
    protected TargetHpBiggerThen300Handler(OnePunchHandler next) {
        super(next);
    }

    @Override
    protected boolean match(Role role) {
        return role.getHp() > 500;
    }


    @Override
    protected void doHandle() {

    }
}
