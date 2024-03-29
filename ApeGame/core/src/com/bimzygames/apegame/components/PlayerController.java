package com.bimzygames.apegame.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.bimzygames.apegame.DIContainer;
import com.bimzygames.apegame.actions.IAction;
import com.bimzygames.apegame.common.Time;
import com.bimzygames.apegame.debug.Logger;
import com.bimzygames.apegame.services.InputService;
import com.bimzygames.apegame.entities.Entity;

public class PlayerController implements IComponent
{
    private Transform _transform;
    private Vector2 _input = new Vector2();
    private float _speed;

    public PlayerController(float speed)
    {
        _speed = speed;
    }

    @Override
    public void Initialize(Entity entity)
    {
        _transform = entity.getTransform();
        InputService inputService = DIContainer.getInstance().resolve(InputService.class);
        inputService.AddKeyDownAction(
            new IAction() {
            @Override
            public void execute(Object... objects) {
                keyDown((int)objects[0]);
            }
        });

        inputService.AddKeyUpAction(
                new IAction() {
                    @Override
                    public void execute(Object... objects) {
                        keyUp((int)objects[0]);
                    }
                });
    }

    @Override
    public void Deinitialize() {

    }

    @Override
    public void update() {
        _input.nor();
        _transform.translate(new Vector2(Time.deltaTime() * _speed * _input.x, Time.deltaTime() * _speed * _input.y));
    }

    public void keyDown(int keycode) {
        switch (keycode)
        {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                _input.x = -1;
                break;

            case Input.Keys.D:
            case Input.Keys.RIGHT:
                _input.x = 1;
                break;

            case Input.Keys.W:
            case Input.Keys.UP:
                _input.y = 1;
                break;

            case Input.Keys.S:
            case Input.Keys.DOWN:
                _input.y = -1;
                break;
        }
    }

    public void keyUp(int keycode) {
        float threshhold = 0.3f;
        switch (keycode)
        {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                if (_input.x < threshhold)
                {
                    _input.x = 0;
                }
                break;

            case Input.Keys.D:
            case Input.Keys.RIGHT:
                if (_input.x > threshhold)
                {
                    _input.x = 0;
                }
                break;

            case Input.Keys.W:
            case Input.Keys.UP:
                if (_input.y > threshhold)
                {
                    _input.y = 0;
                }
                break;

            case Input.Keys.S:
            case Input.Keys.DOWN:
                if (_input.y < threshhold)
                {
                    _input.y = 0;
                }
                break;

        }
    }
}
