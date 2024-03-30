package com.bimzygames.apegame.simulation;

import com.bimzygames.apegame.actions.ActionBinding;
import com.bimzygames.apegame.actions.ActionExecutor;
import com.bimzygames.apegame.actions.IAction;

import java.util.List;

import static com.bimzygames.apegame.common.Math.Clamp;

public class Health
{
    private final int max;
    private int cur;

    public final ActionBinding healthChanged = new ActionBinding();

    public Health(int max)
    {
        this.max = max;
        cur = max;
    }

    public void lose(int amount)
    {
        cur = Clamp(cur - amount, 0, max);

        dispatchHealthChanged();
    }

    public void gain(int amount)
    {
        cur = Clamp(cur + amount, 0, max);

        dispatchHealthChanged();
    }

    private void dispatchHealthChanged()
    {
        ActionExecutor.performAction(healthChanged, cur, max);
    }
}
