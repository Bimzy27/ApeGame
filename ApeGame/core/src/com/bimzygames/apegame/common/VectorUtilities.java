package com.bimzygames.apegame.common;

import com.badlogic.gdx.math.Vector2;

public class VectorUtilities {
    public static boolean WithinBounds(Vector2 input, Vector2 position, Rect rect) {
        int halfX = rect.width / 2;
        int halfY = rect.height / 2;
        if (input.x > position.x - halfX &&
                input.x < position.x + halfX &&
                input.y > position.y - halfY &&
                input.y < position.y + halfY) {
            return true;
        }
        return false;
    }

    public static boolean Intersects(Vector2 pos1, Rect rect1, Vector2 pos2, Rect rect2) {
        // Calculate the coordinates of the edges of each rectangle
        int rect1Left = (int) pos1.x;
        int rect1Right = (int) pos1.x + rect1.width;
        int rect1Top = (int) pos1.y;
        int rect1Bottom = (int) pos1.y + rect1.height;

        int rect2Left = (int) pos2.x;
        int rect2Right = (int) pos2.x + rect2.width;
        int rect2Top = (int) pos2.y;
        int rect2Bottom = (int) pos2.y + rect2.height;

        // Check for intersection
        boolean intersectsX = rect1Left < rect2Right && rect1Right > rect2Left;
        boolean intersectsY = rect1Top < rect2Bottom && rect1Bottom > rect2Top;

        // Return true if there is both X and Y intersection
        return intersectsX && intersectsY;
    }
}
