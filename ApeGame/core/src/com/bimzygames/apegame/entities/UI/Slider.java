package com.bimzygames.apegame.entities.UI;

import com.badlogic.gdx.math.Vector2;
import com.bimzygames.apegame.common.Orientation;
import com.bimzygames.apegame.common.Rect;
import com.bimzygames.apegame.components.renderers.FilledRenderOperation;
import com.bimzygames.apegame.components.renderers.SpriteRenderer;
import com.bimzygames.apegame.entities.Entity;
import com.bimzygames.apegame.entities.cameras.CameraLayer;

public class Slider extends Entity
{
    public Slider(Vector2 position, Rect rect, int sortOrder, CameraLayer cameraLayer)
    {
        super(position,
                new SpriteRenderer(rect, "UI/barBack_horizontalMid.png", sortOrder, cameraLayer),
                new SpriteRenderer(rect, "UI/barRed_horizontalMid.png", sortOrder + 1, cameraLayer));
        SpriteRenderer filledRenderer = (SpriteRenderer) getComponent(2);
        FilledRenderOperation filledRenderOperation = new FilledRenderOperation(Orientation.Horizontal, filledRenderer.getTexture());
        filledRenderOperation.setFillAmount(0.5f);
        filledRenderer.overrideRenderOperation(filledRenderOperation);
    }
    public Slider(Vector2 position, Rect rect, int sortOrder)
    {
        this(position, rect, sortOrder, CameraLayer.UI);
    }
}
