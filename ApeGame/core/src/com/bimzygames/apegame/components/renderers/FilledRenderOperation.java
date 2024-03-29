package com.bimzygames.apegame.components.renderers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bimzygames.apegame.common.Math;
import com.bimzygames.apegame.common.Orientation;

public class FilledRenderOperation implements IRenderOperation
{
    private Orientation _orientation;
    private float _fillAmount = 1f;
    private Texture _texture;
    public void setFillAmount(float fillAmount)
    {
        _fillAmount = fillAmount;
    }
    public float getFillAmount()
    {
        return _fillAmount;
    }

    public FilledRenderOperation(Orientation orientation, Texture texture)
    {
        _orientation = orientation;
        _texture = texture;
    }

    @Override
    public void render(SpriteBatch spriteBatch, float x, float y) {
        TextureRegion region = new TextureRegion(_texture, 0, 0);
        switch (_orientation)
        {
            case Horizontal:
                float regionWidth = _texture.getWidth() * Math.Clamp01(_fillAmount);
                region = new TextureRegion(_texture, 0, 0, (int)regionWidth, _texture.getHeight());
                break;
            case Vertical:
                float regionHeight = _texture.getHeight() * Math.Clamp01(_fillAmount);
                region = new TextureRegion(_texture, 0, 0, _texture.getWidth(), (int)regionHeight);
                break;
        }
        spriteBatch.draw(region, x, y);
    }
}
