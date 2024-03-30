package com.bimzygames.apegame.scenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bimzygames.apegame.DIContainer;
import com.bimzygames.apegame.IUpdater;
import com.bimzygames.apegame.components.IComponent;
import com.bimzygames.apegame.components.Transform;
import com.bimzygames.apegame.entities.Entity;
import com.bimzygames.apegame.entities.Viewport;
import com.bimzygames.apegame.entities.cameras.CameraGame;
import com.bimzygames.apegame.entities.cameras.CameraUI;
import com.bimzygames.apegame.services.UpdateService;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene implements IScene {
    private List<Entity> _entities;
    protected CameraGame _cameraGame;
    protected CameraUI _cameraUI;
    private final SpriteBatch _spriteBatch;

    public Scene() {
        _spriteBatch = DIContainer.getInstance().resolve(SpriteBatch.class);
        CreateCameras();
    }

    protected void CreateCameras() {
        _cameraGame = new CameraGame();
        _cameraUI = new CameraUI();
    }

    @Override
    public void load() {
        _entities = getEntities();
        SetEntityParents();
        for (Entity entity : _entities) {
            entity.Initialize(entity);
        }

        Viewport viewport = DIContainer.getInstance().resolve(Viewport.class);
        viewport.Initialize(viewport);

        UpdateService.getInstance().AddUpdater(this);
    }

    @Override
    public void unload() {
        UpdateService.getInstance().RemoveUpdater(this);

        for (Entity entity : _entities) {
            entity.Deinitialize();
        }
    }

    @Override
    public void update() {
        for (Entity entity : _entities) {
            entity.update();
        }

        _cameraGame.render(_spriteBatch);
        _cameraUI.render(_spriteBatch);
    }

    @Override
    public List<Entity> getEntities() {
        List<Entity> entities = new ArrayList<>();
        entities.add(_cameraGame);
        entities.add(_cameraUI);
        return entities;
    }

    private void SetEntityParents() {
        for (Entity entity : _entities)
        {
            SetParents(entity);
        }
    }

    private void SetParents(Entity entity)
    {
        Transform parent = entity.getTransform();
        for (IComponent component : entity.getComponents())
        {
            if (component instanceof Entity) {
                Entity childEntity = (Entity) component;
                childEntity.setParent(parent);
                SetParents(childEntity);
            }
        }
    }
}
