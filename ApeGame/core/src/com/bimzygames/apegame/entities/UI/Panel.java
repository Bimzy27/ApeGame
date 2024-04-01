package com.bimzygames.apegame.entities.UI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.bimzygames.apegame.common.Orientation;
import com.bimzygames.apegame.common.Rect;
import com.bimzygames.apegame.common.RectBounds;
import com.bimzygames.apegame.components.renderers.FilledRenderOperation;
import com.bimzygames.apegame.components.renderers.NineSliceRenderOperation;
import com.bimzygames.apegame.components.renderers.SpriteRenderer;
import com.bimzygames.apegame.entities.Entity;
import com.bimzygames.apegame.entities.cameras.CameraLayer;

public class Panel extends Entity
{
    public Panel(Vector2 position, RectBounds rectBounds, int sortOrder)
    {
        super(position,
                new SpriteRenderer(rectBounds.rect, "UI/panel_brown.png", sortOrder, CameraLayer.UI));
        SpriteRenderer renderer = getComponent(SpriteRenderer.class);
        NineSliceRenderOperation nineSlice = new NineSliceRenderOperation(rectBounds, new Texture("UI/panel_brown.png"));
        renderer.overrideRenderOperation(nineSlice);
    }
}