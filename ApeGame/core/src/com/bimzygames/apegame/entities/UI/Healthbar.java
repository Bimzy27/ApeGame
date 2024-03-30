package com.bimzygames.apegame.entities.UI;

import com.badlogic.gdx.math.Vector2;
import com.bimzygames.apegame.actions.IAction;
import com.bimzygames.apegame.common.Rect;
import com.bimzygames.apegame.entities.Entity;
import com.bimzygames.apegame.entities.cameras.CameraLayer;
import com.bimzygames.apegame.simulation.Health;

public class Healthbar extends Entity
{
    private Slider slider;
    private Health health;
    private IAction healthChangedAction;

    public Healthbar(Vector2 position, Rect rect, int sortOrder)
    {
        super(position, new Slider(new Vector2(0, 0), rect, sortOrder, CameraLayer.Game));
        slider = getComponent(Slider.class);
        slider.setFill(1f);
    }

    public void setHealth(Health health)
    {
        this.health = health;
        healthChangedAction = new IAction() {
            @Override
            public void execute(Object... objects) {
                int cur = (int)objects[0];
                int max = (int)objects[1];
                slider.setFill((float) cur /max);
            }
        };
        this.health.healthChanged.addAction(healthChangedAction);
    }

    @Override
    public void Deinitialize() {
        this.health.healthChanged.removeAction(healthChangedAction);
        super.Deinitialize();
    }
}
