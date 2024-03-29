package com.bimzygames.apegame.entities;

import com.badlogic.gdx.math.Vector2;
import com.bimzygames.apegame.DIContainer;
import com.bimzygames.apegame.common.Rect;
import com.bimzygames.apegame.components.BoxCollider;
import com.bimzygames.apegame.components.MoveTowards;
import com.bimzygames.apegame.components.renderers.GameSortOrders;
import com.bimzygames.apegame.components.renderers.SpriteRenderer;
import com.bimzygames.apegame.entities.UI.Healthbar;

public class Enemy extends Entity
{
    public Enemy(Rect rect)
    {
        super(new Vector2(-500, 300),
                new BoxCollider(rect),
                new SpriteRenderer(rect, "bat.png", GameSortOrders.Enemies.getValue()),
                new MoveTowards(100f, 0f),
                new Healthbar(new Vector2(0, 100), new Rect(140, 30), GameSortOrders.Enemies.getValue() + 1),
                new SpriteRenderer(rect, "bluePixel.png", GameSortOrders.Enemies.getValue() + 2));
    }

    @Override
    public void Initialize(Entity entity) {
        super.Initialize(entity);
        MoveTowards moveTowards = (MoveTowards) getComponent(3);
        moveTowards.SetTarget(DIContainer.getInstance().resolve(Player.class));
    }
}
