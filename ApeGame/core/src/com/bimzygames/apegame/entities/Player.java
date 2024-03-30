package com.bimzygames.apegame.entities;

import com.badlogic.gdx.math.Vector2;
import com.bimzygames.apegame.DIContainer;
import com.bimzygames.apegame.common.Rect;
import com.bimzygames.apegame.components.BoxCollider;
import com.bimzygames.apegame.components.ModelView;
import com.bimzygames.apegame.components.PlayerController;
import com.bimzygames.apegame.components.renderers.GameSortOrders;
import com.bimzygames.apegame.components.renderers.SpriteRenderer;
import com.bimzygames.apegame.entities.UI.Healthbar;
import com.bimzygames.apegame.simulation.models.PlayerModel;

public class Player extends Entity
{
    public Player(Rect rect)
    {
        super(
                new SpriteRenderer(rect, "knight_rune_trim.png", GameSortOrders.Player.getValue()),
                new PlayerController(150f),
                new Healthbar(new Vector2(0, 100), new Rect(140, 30), GameSortOrders.Player.getValue() + 1),
                new BoxCollider(rect),
                new SpriteRenderer(rect, "bluePixel.png", GameSortOrders.Player.getValue() + 2),
                new ModelView(new PlayerModel()));
        DIContainer.getInstance().register(Player.class, this);

        Healthbar healthbar = getComponent(Healthbar.class);
        PlayerModel playerModel = getComponent(ModelView.class).getModel(PlayerModel.class);
        healthbar.setHealth(playerModel.getHealth());
    }

    @Override
    public void Deinitialize() {
        DIContainer.getInstance().unregister(Player.class);
        super.Deinitialize();
    }
}
