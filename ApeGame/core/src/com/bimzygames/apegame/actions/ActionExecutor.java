package com.bimzygames.apegame.actions;

public class ActionExecutor
{
    public static void performAction(IAction action, Object... objects)
    {
        action.execute(objects);
    }
}
