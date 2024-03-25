package com.bimzygames.apegame.entities;

import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bimzygames.apegame.DIContainer;
import com.bimzygames.apegame.entities.cameras.CameraGame;

import static com.bimzygames.apegame.common.Definitions.ScreenSize;

public class Viewport extends Entity
{
    private FitViewport viewport;

    @Override
    public void Initialize(Entity entity) {
        super.Initialize(entity);

        CameraGame camera = DIContainer.getInstance().resolve(CameraGame.class);
        if (viewport == null)
        {
            viewport = new FitViewport(ScreenSize.width, ScreenSize.height, camera.getCamera());
        }
        else
        {
            viewport.setCamera(camera.getCamera());
        }

        viewport.apply(true);
    }

    public void resize(int width, int height)
    {
        viewport.update(width, height, true);
    }
}
