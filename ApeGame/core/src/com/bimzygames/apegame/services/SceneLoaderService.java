package com.bimzygames.apegame.services;

import com.bimzygames.apegame.DIContainer;
import com.bimzygames.apegame.debug.Logger;
import com.bimzygames.apegame.scenes.IScene;

public class SceneLoaderService
{
    private IScene _activeScene = null;

    public IScene getActiveScene()
    {
        return _activeScene;
    }

    public void LoadScene(IScene scene)
    {
        UnloadScene();
        _activeScene = scene;
        Logger.Log("Scene with name: ", _activeScene.toString(), " was loaded!");
        _activeScene.load();
    }

    public void UnloadScene()
    {
        if (_activeScene != null)
        {
            Logger.Log("Scene with name: ", _activeScene.toString(), " was unloaded!");
            DIContainer.getInstance().resolve(InputService.class).reset();
            _activeScene.unload();
        }
    }
}
