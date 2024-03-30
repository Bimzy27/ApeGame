package com.bimzygames.apegame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bimzygames.apegame.entities.Viewport;
import com.bimzygames.apegame.services.*;

public class Installer
{
    private Object[] getResolvables()
    {
        return new Object[]
                {
                        new SpriteBatch(),
                        new Viewport(),
                        new FontService(),
                        new InputService(),
                        new SceneLoaderService()
                };
    }

    public void BindResolvables()
    {
        for (Object object : getResolvables())
        {
            DIContainer.getInstance().register((Class<Object>)object.getClass(), object);
        }
    }

    public void UnbindResolvables()
    {
        Object[] resolvables = getResolvables();
        for (int i = resolvables.length - 1; i >= 0; i--) {
            Object resolvable = resolvables[i];
            DIContainer.getInstance().unregister(resolvable.getClass());
        }
    }
}
