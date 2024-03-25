package com.bimzygames.apegame.common;

import com.badlogic.gdx.math.Vector2;

public class VectorUtilities {
    public static boolean WithinBounds(Vector2 input, Vector2 position, Rect rect)
    {
        int halfX = rect.width / 2;
        int halfY = rect.height / 2;
        if (input.x > position.x - halfX &&
            input.x < position.x + halfX &&
            input.y > position.y - halfY &&
            input.y < position.y + halfY)
        {
            return true;
        }
        return false;
    }
}
