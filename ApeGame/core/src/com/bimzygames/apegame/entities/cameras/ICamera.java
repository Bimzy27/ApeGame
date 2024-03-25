package com.bimzygames.apegame.entities.cameras;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bimzygames.apegame.components.Transform;
import com.bimzygames.apegame.components.renderers.IRenderer;

public interface ICamera
{
    public Transform getTransform();
    public void addRenderer(IRenderer renderer);
    public void removeRenderer(IRenderer renderer);
    public void render(SpriteBatch spriteBatch);
}
