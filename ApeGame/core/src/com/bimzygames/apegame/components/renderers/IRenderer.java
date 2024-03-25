package com.bimzygames.apegame.components.renderers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IRenderer
{
    public int getSortOrder();
    public void setSortOrder(int sortOrder);
    public void render(SpriteBatch spriteBatch);
}
