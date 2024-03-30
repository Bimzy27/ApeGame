package com.bimzygames.apegame.entities;

import com.badlogic.gdx.math.Vector2;
import com.bimzygames.apegame.DIContainer;
import com.bimzygames.apegame.actions.IAction;
import com.bimzygames.apegame.common.Rect;
import com.bimzygames.apegame.components.BoxCollider;
import com.bimzygames.apegame.components.ModelView;
import com.bimzygames.apegame.components.MoveTowards;
import com.bimzygames.apegame.components.renderers.GameSortOrders;
import com.bimzygames.apegame.components.renderers.SpriteRenderer;
import com.bimzygames.apegame.entities.UI.Healthbar;
import com.bimzygames.apegame.simulation.models.PlayerModel;

public class Enemy extends Entity
{
    public Enemy(Rect rect)
    {
        super(new Vector2(-500, 300),
                new BoxCollider(rect,
                new IAction() {
                    @Override
                    public void execute(Object... objects) {
                        if (((BoxCollider)objects[0]).getTransform().getEntity() instanceof Player)
                        {
                            Player player = (Player) ((BoxCollider)objects[0]).getTransform().getEntity();
                            player.getComponent(ModelView.class).getModel(PlayerModel.class).getHealth().lose(1);
                        }
                    }
                },
                new IAction() {
                    @Override
                    public void execute(Object... objects) {

                    }
                }),
                new SpriteRenderer(rect, "bat.png", GameSortOrders.Enemies.getValue()),
                new MoveTowards(100f, 0f),
                new SpriteRenderer(rect, "bluePixel.png", GameSortOrders.Enemies.getValue() + 1));
    }

    @Override
    public void Initialize(Entity entity) {
        super.Initialize(entity);
        MoveTowards moveTowards = (MoveTowards) getComponent(3);
        moveTowards.SetTarget(DIContainer.getInstance().resolve(Player.class));
    }
}
