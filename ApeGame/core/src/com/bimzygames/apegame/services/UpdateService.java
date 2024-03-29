package com.bimzygames.apegame.services;

import com.bimzygames.apegame.DIContainer;
import com.bimzygames.apegame.IUpdater;
import com.bimzygames.apegame.components.BoxCollider;

import java.util.ArrayList;

public class UpdateService implements IUpdater
{
    private static UpdateService instance;
    public static UpdateService getInstance() {
        if (instance == null) {
            instance = new UpdateService();
        }
        return instance;
    }

    private ArrayList<IUpdater> updaters = new ArrayList<IUpdater>();

    public void AddUpdater(IUpdater updater)
    {
        updaters.add(updater);
    }

    public void RemoveUpdater(IUpdater updater)
    {
        updaters.remove(updater);
    }

    @Override
    public void update() {
        for (IUpdater updater : updaters)
        {
            updater.update();
        }
    }
}
