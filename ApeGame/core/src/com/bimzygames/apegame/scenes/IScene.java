package com.bimzygames.apegame.scenes;

import com.bimzygames.apegame.IUpdater;
import com.bimzygames.apegame.entities.Entity;

import java.util.List;

public interface IScene extends IUpdater
{
    public List<Entity> getEntities();
    public void load();
    public void unload();
    public void update();
}
