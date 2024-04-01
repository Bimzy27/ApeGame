package com.bimzygames.apegame.entities.UI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.bimzygames.apegame.actions.IAction;
import com.bimzygames.apegame.common.Bounds;
import com.bimzygames.apegame.common.Rect;
import com.bimzygames.apegame.common.RectBounds;
import com.bimzygames.apegame.components.ClickReceiver;
import com.bimzygames.apegame.components.renderers.NineSliceRenderOperation;
import com.bimzygames.apegame.components.renderers.SpriteRenderer;
import com.bimzygames.apegame.components.renderers.TextRenderer;
import com.bimzygames.apegame.entities.Entity;
import com.bimzygames.apegame.entities.cameras.CameraLayer;

import static com.bimzygames.apegame.common.CameraUtilities.getCamera;

public class Button extends Entity
{
    public Button(String text, Vector2 position, RectBounds rectBounds, int sortOrder, IAction clickAction)
    {
        super(position,
              new SpriteRenderer(rectBounds.rect, "UI/buttonSquare_blue.png", sortOrder, CameraLayer.UI),
              new TextRenderer(text, new RectBounds(rectBounds.rect, new Bounds(40, 55, 40, 40)), sortOrder + 1, CameraLayer.UI),
              new ClickReceiver(getCamera(CameraLayer.UI), rectBounds.rect, clickAction));

        SpriteRenderer renderer = getComponent(SpriteRenderer.class);
        NineSliceRenderOperation nineSlice = new NineSliceRenderOperation(rectBounds, new Texture("UI/buttonSquare_blue.png"));
        renderer.overrideRenderOperation(nineSlice);
    }
}
