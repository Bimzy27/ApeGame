package com.bimzygames.apegame.entities;

import com.badlogic.gdx.math.Vector2;
import com.bimzygames.apegame.DIContainer;
import com.bimzygames.apegame.common.Rect;
import com.bimzygames.apegame.components.BoxCollider;
import com.bimzygames.apegame.components.renderers.GameSortOrders;
import com.bimzygames.apegame.components.renderers.SpriteRenderer;
import com.bimzygames.apegame.entities.UI.Healthbar;

public class Castle extends Entity
{
    public Castle(Rect rect)
    {
        super(
                new SpriteRenderer(rect, "castle.png", GameSortOrders.Castle.getValue()),
                new Healthbar(new Vector2(0, 100), new Rect(140, 30), GameSortOrders.Castle.getValue() + 1),
                new BoxCollider(rect),
                new SpriteRenderer(rect, "bluePixel.png", GameSortOrders.Castle.getValue() + 2));
        DIContainer.getInstance().register(Castle.class, this);
    }

    @Override
    public void Deinitialize() {
        DIContainer.getInstance().unregister(Castle.class);
        super.Deinitialize();
    }
}
