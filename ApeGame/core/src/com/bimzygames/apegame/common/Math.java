package com.bimzygames.apegame.common;

import com.badlogic.gdx.math.Vector2;

public class Math {
    public static float Clamp(float value, float lower, float upper)
    {
        if (value < lower)
        {
            return lower;
        }
        if (value > upper)
        {
            return upper;
        }
        return value;
    }

    public static float Clamp01(float value)
    {
        return Clamp(value, 0f, 1f);
    }

    public static float Distance(Vector2 pos1, Vector2 pos2)
    {
        return pos1.dst(pos2);
    }

    public static Vector2 Lerp(Vector2 start, Vector2 end, float alpha) {
        return new Vector2(start.x + alpha * (end.x - start.x), start.y + alpha * (end.y - start.y));
    }
}