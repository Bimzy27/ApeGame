package com.bimzygames.apegame.components.renderers;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bimzygames.apegame.DIContainer;
import com.bimzygames.apegame.common.RectBounds;
import com.bimzygames.apegame.components.IComponent;
import com.bimzygames.apegame.components.Transform;
import com.bimzygames.apegame.entities.Entity;
import com.bimzygames.apegame.entities.cameras.CameraGame;
import com.bimzygames.apegame.entities.cameras.CameraLayer;
import com.bimzygames.apegame.entities.cameras.CameraUI;
import com.bimzygames.apegame.entities.cameras.ICamera;

public class TextRenderer implements IComponent, IRenderer
{
    private RectBounds _rectBounds;
    private CameraLayer _cameraLayer;
    private BitmapFont _font;
    private String _text;
    private int _sortOrder;
    private Transform _transform;
    private ICamera _camera;

    public TextRenderer(String text, RectBounds rectBounds, int sortOrder, CameraLayer cameraLayer)
    {
        _font = new BitmapFont();
        _text = text;
        _rectBounds = rectBounds;
        _sortOrder = sortOrder;
        _cameraLayer = cameraLayer;
    }

    @Override
    public void Initialize(Entity entity)
    {
        switch (_cameraLayer)
        {
            case Game:
                _camera = DIContainer.getInstance().resolve(CameraGame.class);
                break;
            case UI:
                _camera = DIContainer.getInstance().resolve(CameraUI.class);
                break;
        }

        _camera.addRenderer(this);
        _transform = entity.getTransform();
    }

    @Override
    public void Deinitialize() {
        _font.dispose();
        _camera.removeRenderer(this);
    }

    @Override
    public void update() {
    }

    @Override
    public int getSortOrder() {
        return _sortOrder;
    }

    @Override
    public void setSortOrder(int sortOrder) {
        _sortOrder = sortOrder;
    }

    private float getX()
    {
        float centerX = _transform.position.x + ((-_rectBounds.bounds.right + _rectBounds.bounds.left) * 0.5f);
        GlyphLayout layout = new GlyphLayout(_font, _text);
        return centerX - layout.width / 2f;
    }

    private float getY()
    {
        float centerY = _transform.position.y + ((-_rectBounds.bounds.top + _rectBounds.bounds.bottom) * 0.5f);
        GlyphLayout layout = new GlyphLayout(_font, _text);
        return centerY + layout.height / 2f;
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        float fontScaleX = calculateFontScaleX();
        float fontScaleY = calculateFontScaleY();
        _font.getData().setScale(fontScaleX, fontScaleY);
        _font.draw(spriteBatch, _text, getX(), getY());
        _font.getData().setScale(1.0f);
    }

    private float calculateFontScaleX() {
        GlyphLayout layout = new GlyphLayout(_font, _text);
        float width = _rectBounds.getLeft() + _rectBounds.getRight();
        return width / layout.width;
    }

    private float calculateFontScaleY() {
        GlyphLayout layout = new GlyphLayout(_font, _text);
        float rectHeight = _rectBounds.getTop() + _rectBounds.getBottom();
        return rectHeight / layout.height;
    }
}
