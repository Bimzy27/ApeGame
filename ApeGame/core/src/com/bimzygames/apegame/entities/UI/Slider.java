package com.bimzygames.apegame.entities.UI;

import com.badlogic.gdx.math.Vector2;
import com.bimzygames.apegame.common.Orientation;
import com.bimzygames.apegame.common.Rect;
import com.bimzygames.apegame.components.renderers.FilledRenderOperation;
import com.bimzygames.apegame.components.renderers.SpriteRenderer;
import com.bimzygames.apegame.entities.Entity;
import com.bimzygames.apegame.entities.cameras.CameraLayer;

import static com.bimzygames.apegame.common.Math.Clamp01;

public class Slider extends Entity
{
    private FilledRenderOperation filledRenderOperation;

    public Slider(Vector2 position, Rect rect, int sortOrder, CameraLayer cameraLayer)
    {
        super(position,
                new SpriteRenderer(rect, "UI/barBack_horizontalMid.png", sortOrder, cameraLayer),
                new SpriteRenderer(rect, "UI/barRed_horizontalMid.png", sortOrder + 1, cameraLayer));
        SpriteRenderer filledRenderer = (SpriteRenderer) getComponent(2);
        filledRenderOperation = new FilledRenderOperation(Orientation.Horizontal, filledRenderer.getTexture());
        filledRenderOperation.setFillAmount(1f);
        filledRenderer.overrideRenderOperation(filledRenderOperation);
    }
    public Slider(Vector2 position, Rect rect, int sortOrder)
    {
        this(position, rect, sortOrder, CameraLayer.UI);
    }

    public void setFill(float fillAmount)
    {
        fillAmount = Clamp01(fillAmount);
        filledRenderOperation.setFillAmount(fillAmount);

    }
}
