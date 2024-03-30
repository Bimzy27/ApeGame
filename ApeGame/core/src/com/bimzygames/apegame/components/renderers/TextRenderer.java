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
import com.bimzygames.apegame.services.FontService;

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
        _font = DIContainer.getInstance().resolve(FontService.class).headerFont;
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

    @Override
    public float getX()
    {
        float centerX = _transform.getPosition().x + ((-_rectBounds.bounds.right + _rectBounds.bounds.left) * 0.5f);
        GlyphLayout layout = new GlyphLayout(_font, _text);
        return (centerX - layout.width / 2f) * calculateFontScaleX();
    }

    @Override
    public float getY()
    {
        float centerY = _transform.getPosition().y + ((-_rectBounds.bounds.top + _rectBounds.bounds.bottom) * 0.5f);
        GlyphLayout layout = new GlyphLayout(_font, _text);
        return (centerY + layout.height / 2f) * calculateFontScaleX();
    }

    @Override
    public IRenderOperation getRenderOperation() {
        return this;
    }

    @Override
    public void render(SpriteBatch spriteBatch, float x, float y) {
        float fontScaleX = calculateFontScaleX();
        float fontScaleY = calculateFontScaleY();
        _font.getData().setScale(fontScaleX, fontScaleY);
        _font.draw(spriteBatch, _text, x, y);
        _font.getData().setScale(1.0f);
    }

    private float calculateFontScaleX() {
        GlyphLayout layout = new GlyphLayout(_font, _text);
        float width = _rectBounds.getBoundedRect().width;
        return width / layout.width;
    }

    private float calculateFontScaleY() {
        GlyphLayout layout = new GlyphLayout(_font, _text);
        float height = _rectBounds.getBoundedRect().height;
        return height / layout.height;
    }
}
