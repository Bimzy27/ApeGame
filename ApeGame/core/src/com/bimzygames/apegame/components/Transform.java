package com.bimzygames.apegame.components;

import com.badlogic.gdx.math.Vector2;
import com.bimzygames.apegame.entities.Entity;

public class Transform implements IComponent, Cloneable
{
    private Vector2 position = new Vector2(0, 0);
    private Transform parent;
    private Entity entity;

    public Transform(Entity entity, Transform transform)
    {
        this.entity = entity;
        this.position = transform.getPosition();
        this.parent = transform.getParent();
    }
    public Transform(Entity entity, Vector2 position) {
        this.entity = entity;
        this.position = position;
    }

    public Transform(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity()
    {
        return entity;
    }
    public Vector2 getPosition()
    {
        return position;
    }

    public void setPosition(Vector2 position)
    {
        this.position = position;
    }

    public void translate(Vector2 movement)
    {
        position.add(movement);
    }

    public void translate(float x, float y)
    {
        position.add(x, y);
    }

    public Transform getParent()
    {
        return parent;
    }
    public void setParent(Transform parent)
    {
        this.parent = parent;
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