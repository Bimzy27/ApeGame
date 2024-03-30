package com.bimzygames.apegame.entities.cameras;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.bimzygames.apegame.DIContainer;
import com.bimzygames.apegame.components.IComponent;
import com.bimzygames.apegame.components.Transform;
import com.bimzygames.apegame.entities.Entity;
import com.bimzygames.apegame.components.renderers.IRenderer;

import java.util.*;

import static com.bimzygames.apegame.common.Definitions.ScreenSize;

public class CameraGame extends Entity implements ICamera
{
    private OrthographicCamera _camera;
    private TreeMap<Integer, IRenderer> _sortedRenderers = new TreeMap<>();

    public OrthographicCamera getCamera() {
        return _camera;
    }

    public CameraGame(IComponent... components)
    {
        super(components);
        DIContainer.getInstance().register(CameraGame.class, this);
    }

    @Override
    public void Initialize(Entity entity) {
        super.Initialize(entity);
        _camera = new OrthographicCamera();
        _camera.setToOrtho(false, ScreenSize.width, ScreenSize.height);
    }

    @Override
    public void Deinitialize() {
        DIContainer.getInstance().unregister(CameraGame.class);
        super.Deinitialize();
    }

    public void addRenderer(IRenderer renderer)
    {
        int sortOrder = renderer.getSortOrder();
        boolean rendererAdded = false;
        while (rendererAdded == false)
        {
            if (_sortedRenderers.containsKey(sortOrder) == false)
            {
                _sortedRenderers.put(sortOrder, renderer);
                rendererAdded = true;
                if (sortOrder != renderer.getSortOrder())
                {
                    renderer.setSortOrder(sortOrder);
                }
            }
            else
            {
                sortOrder ++;
            }
        }
    }

    public void removeRenderer(IRenderer renderer)
    {
        int sortOrder = renderer.getSortOrder();
        if (_sortedRenderers.containsKey(sortOrder))
        {
            _sortedRenderers.remove(sortOrder);
        }
    }

    public void render(SpriteBatch spriteBatch)
    {
        spriteBatch.begin();
        _camera.position.set(getTransform().getPosition().x, getTransform().getPosition().y, 0);
        _camera.update();
        spriteBatch.setProjectionMatrix(_camera.combined);
        for (IRenderer renderer : _sortedRenderers.values())
        {
            renderer.getRenderOperation().render(spriteBatch, renderer.getX(), renderer.getY());
        }
        spriteBatch.end();
    }
}
