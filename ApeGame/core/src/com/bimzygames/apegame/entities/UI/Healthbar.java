package com.bimzygames.apegame.entities.UI;

import com.badlogic.gdx.math.Vector2;
import com.bimzygames.apegame.common.Rect;
import com.bimzygames.apegame.entities.Entity;
import com.bimzygames.apegame.entities.cameras.CameraLayer;

public class Healthbar extends Entity
{
    public Healthbar(Vector2 position, Rect rect, int sortOrder)
    {
        super(position, new Slider(new Vector2(0, 0), rect, sortOrder, CameraLayer.Game));
    }
}
