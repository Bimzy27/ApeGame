package com.bimzygames.apegame.components;

import com.badlogic.gdx.math.Vector2;
import com.bimzygames.apegame.entities.Entity;

public class Transform implements IComponent
{
    public Vector2 position = new Vector2();

    public void translate(Vector2 movement)
    {
        position.add(movement);
    }

    public void translate(float x, float y)
    {
        position.add(x, y);
    }

    @Override
    public void Initialize(Entity entity) {

    }

    @Override
    public void Deinitialize() {

    }

    @Override
    public void update() {

    }
}