package com.bimzygames.apegame.entities;

import com.badlogic.gdx.math.Vector2;
import com.bimzygames.apegame.components.IComponent;
import com.bimzygames.apegame.components.Transform;
import com.bimzygames.apegame.debug.Logger;

import java.util.ArrayList;

public class Entity implements IComponent
{
    private ArrayList<IComponent> _components;

    public Transform getTransform() {
        for (IComponent component : _components) {
            if (component instanceof Transform) {
                return (Transform) component;
            }
        }
        return null;
    }

    public Entity(Vector2 position, IComponent... components) {
        this(components);
        getTransform().position = position;
    }

    public Entity(IComponent... components) {
        _components = new ArrayList<>();
        Transform transform = new Transform();
        _components.add(transform);
        for (IComponent component : components) {
            _components.add(component);
        }
    }

    public void addComponent(IComponent component)
    {
        _components.add(component);
    }

    @Override
    public void Initialize(Entity entity) {

        Logger.Log(this.getClass().getName(), " Initialized");

        for (IComponent component : _components)
        {
            component.Initialize(this);
        }
    }

    @Override
    public void Deinitialize()
    {
        for (IComponent component : _components)
        {
            component.Deinitialize();
        }

        Logger.Log(this.getClass().getName(), " Deinitialized");
    }

    @Override
    public void update()
    {
        for (IComponent component : _components)
        {
            component.update();
        }
    }
}