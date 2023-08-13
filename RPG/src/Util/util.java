package Util;

import Base.Role;
import action.Action;


public class util {
    public static void printf(String format, Object... args) {
        System.out.printf(format, args);
    }

    public static void printActionName(Role role) {
        StringBuilder actionName = new StringBuilder();
        for (Action action : role.getActions()) {
            int index = role.getActions().indexOf(action);
            actionName.append("(")
                    .append(index)
                    .append(") ").append(action.getName())
                    .append(" ");
        }
        System.out.printf("選擇行動: %s \n", actionName);

    }
}
