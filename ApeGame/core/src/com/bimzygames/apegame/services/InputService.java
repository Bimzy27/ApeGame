package com.bimzygames.apegame.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.bimzygames.apegame.DIContainer;
import com.bimzygames.apegame.actions.ActionExecutor;
import com.bimzygames.apegame.actions.IAction;

import java.util.ArrayList;
import java.util.List;

import static com.bimzygames.apegame.common.Definitions.ScreenSize;

public class InputService implements InputProcessor
{
    private List<IAction> keyDownActions;
    private List<IAction> keyUpActions;
    private List<IAction> touchDownActions;

    public InputService()
    {
        keyDownActions = new ArrayList<IAction>();
        keyUpActions = new ArrayList<IAction>();
        touchDownActions = new ArrayList<IAction>();
        Gdx.input.setInputProcessor(this);
    }

    public void AddKeyDownAction(IAction action)
    {
        keyDownActions.add(action);
    }

    public void AddKeyUpAction(IAction action)
    {
        keyUpActions.add(action);
    }
    public void AddTouchDownAction(IAction action)
    {
        touchDownActions.add(action);
    }

    public void reset()
    {
        keyDownActions = new ArrayList<IAction>();
        keyUpActions = new ArrayList<IAction>();
        touchDownActions = new ArrayList<IAction>();
    }

    @Override
    public boolean keyDown(int keycode) {
        for (IAction action : keyDownActions)
        {
            ActionExecutor.performAction(action, keycode);
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        for (IAction action : keyUpActions)
        {
            ActionExecutor.performAction(action, keycode);
        }

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenX -= ScreenSize.width / 2;
        screenY -= ScreenSize.height / 2;
        screenY *= -1;
        for (IAction action : touchDownActions)
        {
            ActionExecutor.performAction(action, screenX, screenY, pointer, button);
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
