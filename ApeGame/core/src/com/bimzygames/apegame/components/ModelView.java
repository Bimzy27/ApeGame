package com.bimzygames.apegame.components;

import com.bimzygames.apegame.entities.Entity;
import com.bimzygames.apegame.simulation.models.IModel;

public class ModelView implements IComponent
{
    private final IModel model;

    public ModelView(IModel model)
    {
        this.model = model;
    }

    public <T> T getModel(Class<T> tClass) {
        if (tClass.isAssignableFrom(model.getClass())) {
            return tClass.cast(model);
        }
        return null;
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
