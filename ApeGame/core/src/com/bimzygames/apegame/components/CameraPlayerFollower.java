package com.bimzygames.apegame.components;

import com.bimzygames.apegame.DIContainer;
import com.bimzygames.apegame.entities.Entity;
import com.bimzygames.apegame.entities.Player;

public class CameraPlayerFollower implements IComponent
{
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
        _transform.position = _player.getTransform().position;
    }
}