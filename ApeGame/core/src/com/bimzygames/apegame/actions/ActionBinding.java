package com.bimzygames.apegame.actions;

import java.util.ArrayList;
import java.util.List;

public class ActionBinding
{
    private List<IAction> actions = new ArrayList<>();

    public void addAction(IAction action)
    {
        actions.add(action);
    }

    public void removeAction(IAction action)
    {
        actions.remove(action);
    }

    public List<IAction> getActions()
    {
        return actions;
    }
}