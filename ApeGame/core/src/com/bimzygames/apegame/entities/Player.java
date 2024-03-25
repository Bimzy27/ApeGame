package com.bimzygames.apegame.entities;

import com.bimzygames.apegame.DIContainer;
import com.bimzygames.apegame.actions.IAction;
import com.bimzygames.apegame.common.Rect;
import com.bimzygames.apegame.components.ClickReceiver;
import com.bimzygames.apegame.components.PlayerController;
import com.bimzygames.apegame.components.renderers.GameSortOrders;
import com.bimzygames.apegame.components.renderers.SpriteRenderer;
import com.bimzygames.apegame.entities.cameras.CameraLayer;
import com.bimzygames.apegame.scenes.GameScene;
import com.bimzygames.apegame.scenes.MenuScene;
import com.bimzygames.apegame.services.SceneLoaderService;

import static com.bimzygames.apegame.common.CameraUtilities.getCamera;

public class Player extends Entity
{
    public Player(Rect rect)
    {
        super(new SpriteRenderer(rect, "knight_rune_trim.png", GameSortOrders.Player.getValue()),
                new PlayerController(150f));
        DIContainer.getInstance().register(Player.class, this);
    }

    @Override
    public void Deinitialize() {
        DIContainer.getInstance().unregister(Player.class);
        super.Deinitialize();
    }
}
