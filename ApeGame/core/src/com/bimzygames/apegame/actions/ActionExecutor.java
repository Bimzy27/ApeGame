package com.bimzygames.apegame.actions;

public class ActionExecutor
{
    public static void performAction(IAction action, Object... objects)
    {
        action.execute(objects);
    }

    public static void performAction(ActionBinding actionBinding, Object... objects)
    {
        for (IAction action : actionBinding.getActions())
        {
            action.execute(objects);
        }
    }
}
