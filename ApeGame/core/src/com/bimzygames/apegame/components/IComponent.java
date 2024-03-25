package com.bimzygames.apegame.components;

import com.bimzygames.apegame.entities.Entity;

public interface IComponent
{
    public void Initialize(Entity entity);
    public void Deinitialize();
    public void update();
}
