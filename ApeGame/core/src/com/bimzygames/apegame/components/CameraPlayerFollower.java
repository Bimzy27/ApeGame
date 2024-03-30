package com.bimzygames.apegame.components;

import com.bimzygames.apegame.DIContainer;
import com.bimzygames.apegame.entities.Entity;
import com.bimzygames.apegame.entities.Player;

import static com.bimzygames.apegame.common.Math.Distance;
import static com.bimzygames.apegame.common.VectorUtilities.Lerp;

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
        float dst = Distance(_player.getTransform().getPosition(), _transform.getPosition());
        if (dst <= offset)
        {
            return;
        }
        _transform.setPosition(Lerp(_transform.getPosition(), _player.getTransform().getPosition(), (dst-offset)/lerpStrength));
    }
}