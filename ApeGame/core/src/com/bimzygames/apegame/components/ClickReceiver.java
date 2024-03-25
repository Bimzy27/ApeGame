package com.bimzygames.apegame.components;

import com.badlogic.gdx.math.Vector2;
import com.bimzygames.apegame.DIContainer;
import com.bimzygames.apegame.actions.IAction;
import com.bimzygames.apegame.common.Rect;
import com.bimzygames.apegame.entities.Entity;
import com.bimzygames.apegame.entities.cameras.ICamera;
import com.bimzygames.apegame.services.InputService;

import static com.bimzygames.apegame.common.VectorUtilities.WithinBounds;

public class ClickReceiver implements IComponent
{
    private Transform _transform;
    private ICamera _camera;
    private Rect _rect;
    private IAction _clickAction;

    public ClickReceiver(ICamera camera, Rect rect, IAction clickAction)
    {
        _camera = camera;
        _rect = rect;
        _clickAction = clickAction;
    }

    @Override
    public void Initialize(Entity entity) {
        _transform = entity.getTransform();
        InputService inputService = DIContainer.getInstance().resolve(InputService.class);
        inputService.AddTouchDownAction(
            new IAction() {
                @Override
                public void execute(Object... objects) {
                    touchDown((int)objects[0], (int)objects[1], (int)objects[2], (int)objects[3]);
                }
            });
    }

    @Override
    public void Deinitialize() {

    }

    @Override
    public void update() {

    }

    public void touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 input = new Vector2(screenX, screenY).add(_camera.getTransform().position);
        if (WithinBounds(input, _transform.position, _rect))
        {
            _clickAction.execute();
        }
    }
}
