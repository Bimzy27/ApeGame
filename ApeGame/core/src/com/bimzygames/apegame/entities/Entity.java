package com.bimzygames.apegame.entities;

import com.badlogic.gdx.math.Vector2;
import com.bimzygames.apegame.components.IComponent;
import com.bimzygames.apegame.components.Transform;
import com.bimzygames.apegame.debug.Logger;

import java.util.ArrayList;
import java.util.List;

public class Entity implements IComponent
{
    private ArrayList<IComponent> _components;
    public Entity(Vector2 position, IComponent... components) {
        this(components);
        getTransform().setPosition(position);
    }

    public Entity(IComponent... components) {
        _components = new ArrayList<>();
        Transform transform = new Transform(this, new Vector2(0, 0));
        _components.add(transform);
        for (IComponent component : components) {
            _components.add(component);
        }
    }
    public Transform getTransform() {
        for (IComponent component : _components) {
            if (component instanceof Transform) {
                return (Transform) component;
            }
        }
        return null;
    }

    public void setParent(Transform parent)
    {
        getTransform().setParent(parent);
    }

    public void addComponent(IComponent component)
    {
        _components.add(component);
    }
    public IComponent getComponent(int index)
    {
        if (index < _components.stream().count())
        {
            return _components.get(index);
        }

        throw new IndexOutOfBoundsException();
    }

    public <T> T getComponent(Class<T> tClass) {
        for (IComponent component : _components) {
            if (tClass.isInstance(component)) {
                return tClass.cast(component);
            }
        }
        return null;
    }
    public List<IComponent> getComponents()
    {
        return _components;
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