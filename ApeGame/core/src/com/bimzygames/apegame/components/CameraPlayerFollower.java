package com.bimzygames.apegame.components;

import com.badlogic.gdx.math.Vector2;
import com.bimzygames.apegame.DIContainer;
import com.bimzygames.apegame.common.Time;
import com.bimzygames.apegame.entities.Entity;
import com.bimzygames.apegame.entities.Player;

import static com.bimzygames.apegame.common.Math.Distance;
import static com.bimzygames.apegame.common.Math.Lerp;

public class CameraPlayerFollower implements IComponent
{
    private float offset;
    private float lerpStrength;

    public CameraPlayerFollower(float offset, float lerpStrength)
    {
        this.offset = offset;
        this.lerpStrength = lerpStrength;
    }

    private Transform _transform;
    private Player _player;

    @Override
    public void Initialize(Entity entity)
    {
        _transform = entity.getTransform();
        _player = DIContainer.getInstance().resolve(Player.class);
    }

    @Override
    public void Deinitialize() {
    }

    @Override
    public void update() {
        float dst = Distance(_player.getTransform().position, _transform.position);
        if (dst <= offset)
        {
            return;
        }
        _transform.position = Lerp(_transform.position, _player.getTransform().position, (dst-offset)/lerpStrength);
    }
}