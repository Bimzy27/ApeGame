package com.bimzygames.apegame.components.renderers;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.bimzygames.apegame.common.Rect;
import com.bimzygames.apegame.common.RectBounds;

public class NineSliceRenderOperation implements IRenderOperation
{
    private RectBounds _rectBounds;
    private Texture _texture;

    public NineSliceRenderOperation(RectBounds rectBounds, Texture texture)
    {
        _rectBounds = rectBounds;
        _texture = texture;
    }

    @Override
    public void render(SpriteBatch spriteBatch, float x, float y) {

        int width = _rectBounds.rect.width;
        int height = _rectBounds.rect.height;

        int textureWidth = _texture.getWidth();
        int textureHeight = _texture.getHeight();

        int leftSliceWidth = _rectBounds.bounds.left;
        int topSliceHeight = _rectBounds.bounds.top;
        int rightSliceWidth = _rectBounds.bounds.right;
        int bottomSliceHeight = _rectBounds.bounds.bottom;

        int texWidth = textureWidth - leftSliceWidth - rightSliceWidth;
        int texHeight = textureHeight - topSliceHeight - bottomSliceHeight;
        int coreWidth = width - leftSliceWidth - rightSliceWidth;
        int coreHeight = height - topSliceHeight - bottomSliceHeight;

        RenderSlice(spriteBatch, new Vector2(x, y), new Vector2(0, 0), new Rect(leftSliceWidth, topSliceHeight), new Vector2(0, 0), new Rect(leftSliceWidth, topSliceHeight)); // top left
        RenderSlice(spriteBatch, new Vector2(x, y), new Vector2(leftSliceWidth, 0), new Rect(texWidth, topSliceHeight), new Vector2(leftSliceWidth, 0), new Rect(coreWidth, topSliceHeight)); // top center
        RenderSlice(spriteBatch, new Vector2(x, y), new Vector2(leftSliceWidth + texWidth, 0), new Rect(rightSliceWidth, topSliceHeight), new Vector2(leftSliceWidth + coreWidth, 0), new Rect(rightSliceWidth, topSliceHeight)); // top right

        RenderSlice(spriteBatch, new Vector2(x, y), new Vector2(0, topSliceHeight), new Rect(leftSliceWidth, texHeight), new Vector2(0, topSliceHeight), new Rect(leftSliceWidth, coreHeight)); // center left
        RenderSlice(spriteBatch, new Vector2(x, y), new Vector2(leftSliceWidth, topSliceHeight), new Rect(texWidth, texHeight), new Vector2(leftSliceWidth, topSliceHeight), new Rect(coreWidth, coreHeight)); // center center
        RenderSlice(spriteBatch, new Vector2(x, y), new Vector2(leftSliceWidth + texWidth, topSliceHeight), new Rect(rightSliceWidth, texHeight), new Vector2(leftSliceWidth + coreWidth, topSliceHeight), new Rect(rightSliceWidth, coreHeight)); // center right

        RenderSlice(spriteBatch, new Vector2(x, y), new Vector2(0, topSliceHeight + texHeight), new Rect(leftSliceWidth, bottomSliceHeight), new Vector2(0, topSliceHeight + coreHeight), new Rect(leftSliceWidth, bottomSliceHeight)); // bottom left
        RenderSlice(spriteBatch, new Vector2(x, y), new Vector2(leftSliceWidth, topSliceHeight + texHeight), new Rect(texWidth, bottomSliceHeight), new Vector2(leftSliceWidth, topSliceHeight + coreHeight), new Rect(coreWidth, bottomSliceHeight)); // bottom center
        RenderSlice(spriteBatch, new Vector2(x, y), new Vector2(leftSliceWidth + texWidth, topSliceHeight + texHeight), new Rect(rightSliceWidth, bottomSliceHeight), new Vector2(leftSliceWidth + coreWidth, topSliceHeight + coreHeight), new Rect(rightSliceWidth, bottomSliceHeight)); // bottom right
    }

    private void RenderSlice(SpriteBatch spriteBatch, Vector2 pos, Vector2 srcPos, Rect srcRect, Vector2 dstPos, Rect dstRect)
    {
        TextureData textureData = _texture.getTextureData();
        if (!textureData.isPrepared()) {
            textureData.prepare(); // Prepare the texture data, to ensure the pixmap is not null.
        }
        Pixmap pixmap = new Pixmap(_rectBounds.rect.width, _rectBounds.rect.height, Pixmap.Format.RGBA8888);
        pixmap.drawPixmap(_texture.getTextureData().consumePixmap(),
                (int)srcPos.x, (int)srcPos.y, srcRect.width, srcRect.height,
                (int)dstPos.x, (int)dstPos.y, dstRect.width, dstRect.height);
        spriteBatch.draw(new Texture(pixmap), pos.x, pos.y);
        pixmap.dispose();
    }
}
