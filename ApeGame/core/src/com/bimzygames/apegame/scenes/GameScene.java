package com.bimzygames.apegame.scenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.bimzygames.apegame.DIContainer;
import com.bimzygames.apegame.actions.IAction;
import com.bimzygames.apegame.common.Rect;
import com.bimzygames.apegame.components.CameraPlayerFollower;
import com.bimzygames.apegame.components.ClickReceiver;
import com.bimzygames.apegame.components.renderers.GameSortOrders;
import com.bimzygames.apegame.entities.Viewport;
import com.bimzygames.apegame.entities.cameras.CameraLayer;
import com.bimzygames.apegame.components.renderers.SpriteRenderer;
import com.bimzygames.apegame.entities.Entity;
import com.bimzygames.apegame.entities.Player;
import com.bimzygames.apegame.entities.cameras.CameraGame;
import com.bimzygames.apegame.entities.cameras.CameraUI;
import com.bimzygames.apegame.services.SceneLoaderService;

import java.util.ArrayList;
import java.util.List;

import static com.bimzygames.apegame.common.CameraUtilities.getCamera;

public class GameScene extends Scene
{
    @Override
    protected void CreateCameras() {
        _cameraGame = new CameraGame(new CameraPlayerFollower());
        _cameraUI = new CameraUI();
    }

    @Override
    public List<Entity> getEntities()
    {
        Entity tree = new Entity(new Vector2(200, 200),
                new SpriteRenderer(new Rect(117, 128), "tree_oak.png", GameSortOrders.Foreground.getValue()));
        Entity healthbar = new Entity(new Vector2(0, 450), new SpriteRenderer(new Rect(400, 60), "UI/barRed_horizontalMid.png", GameSortOrders.Healthbar.getValue(), CameraLayer.UI));

        List<Entity> entities = new ArrayList<>(super.getEntities());
        entities.add(tree);
        entities.add(new Player(new Rect(35, 64)));
        entities.add(healthbar);
        return entities;
    }
}
