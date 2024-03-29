package com.bimzygames.apegame.common;

import com.badlogic.gdx.Gdx;

public class Time {
    public static float deltaTime()
    {
        return Gdx.graphics.getDeltaTime();
    }
}
