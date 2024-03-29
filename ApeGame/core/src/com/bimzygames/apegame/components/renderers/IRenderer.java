package com.bimzygames.apegame.components.renderers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IRenderer extends IRenderOperation
{
    public int getSortOrder();
    public void setSortOrder(int sortOrder);
    public float getX();
    public float getY();
    public IRenderOperation getRenderOperation();
}
