package com.bimzygames.apegame.components.renderers;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.bimzygames.apegame.DIContainer;
import com.bimzygames.apegame.common.Rect;
import com.bimzygames.apegame.components.IComponent;
import com.bimzygames.apegame.components.Transform;
import com.bimzygames.apegame.entities.Entity;
import com.bimzygames.apegame.entities.cameras.CameraGame;
import com.bimzygames.apegame.entities.cameras.CameraLayer;
import com.bimzygames.apegame.entities.cameras.CameraUI;
import com.bimzygames.apegame.entities.cameras.ICamera;

public class SpriteRenderer implements IComponent, IRenderer
{
    private final Rect _rect;
    private final CameraLayer _cameraLayer;
    private final Texture _texture;
    private int _sortOrder;
    private Transform _transform;
    private Texture _modifiedTexture;
    private ICamera _camera;

    public SpriteRenderer(Rect rect, Texture texture, int sortOrder, CameraLayer cameraLayer)
    {
        _rect = rect;
        _texture = texture;
        TextureData textureData = texture.getTextureData();
        if (!textureData.isPrepared()) {
            textureData.prepare(); // Prepare the texture data, to ensure the pixmap is not null.
        }
        _sortOrder = sortOrder;
        _cameraLayer = cameraLayer;
        RefreshTexture();
    }

    public SpriteRenderer(Rect rect, String spritePath, int sortOrder, CameraLayer cameraLayer)
    {
        this(rect, new Texture(spritePath), sortOrder, cameraLayer);
    }

    public SpriteRenderer(Rect rect, String spritePath, int sortOrder)
    {
        this(rect, new Texture(spritePath), sortOrder, CameraLayer.Game);
    }

    public SpriteRenderer(String spritePath, int sortOrder, CameraLayer cameraLayer)
    {
        this(new Rect(new Texture(spritePath).getWidth(), new Texture(spritePath).getHeight()), new Texture(spritePath), sortOrder, cameraLayer);
    }

    public SpriteRenderer(String spritePath, int sortOrder)
    {
        this(new Rect(new Texture(spritePath).getWidth(), new Texture(spritePath).getHeight()), new Texture(spritePath), sortOrder, CameraLayer.Game);
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
        _texture.dispose();
        _camera.removeRenderer(this);
    }

    @Override
    public void update() {
        if (_modifiedTexture.getWidth() != _rect.width || _modifiedTexture.getHeight() != _rect.height)
        {
            RefreshTexture();
        }
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
        return _transform.position.x - (_rect.width * 0.5F);
    }

    private float getY()
    {
        return _transform.position.y - (_rect.height * 0.5F);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.draw(_modifiedTexture, getX(), getY());
    }

    private void RefreshTexture()
    {
        Pixmap pixmap = new Pixmap( _rect.width, _rect.height, Pixmap.Format.RGBA8888);
        pixmap.drawPixmap(_texture.getTextureData().consumePixmap(),
                0, 0, _texture.getWidth(), _texture.getHeight(),
                0, 0, _rect.width, _rect.height);

        _modifiedTexture = new Texture(pixmap);
        pixmap.dispose();
    }
}
