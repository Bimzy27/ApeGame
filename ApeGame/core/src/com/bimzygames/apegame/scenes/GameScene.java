package com.bimzygames.apegame.scenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.bimzygames.apegame.DIContainer;
import com.bimzygames.apegame.actions.IAction;
import com.bimzygames.apegame.common.Rect;
import com.bimzygames.apegame.components.CameraPlayerFollower;
import com.bimzygames.apegame.components.ClickReceiver;
import com.bimzygames.apegame.components.renderers.GameSortOrders;
import com.bimzygames.apegame.entities.*;
import com.bimzygames.apegame.entities.UI.Healthbar;
import com.bimzygames.apegame.entities.UI.Slider;
import com.bimzygames.apegame.entities.cameras.CameraLayer;
import com.bimzygames.apegame.components.renderers.SpriteRenderer;
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
        _cameraGame = new CameraGame(new CameraPlayerFollower(250, 750));
        _cameraUI = new CameraUI();
    }

    @Override
    public List<Entity> getEntities()
    {
        Entity tree = new Entity(new Vector2(200, 200),
                new SpriteRenderer(new Rect(117, 128), "tree_oak.png", GameSortOrders.Foreground.getValue()));

        List<Entity> entities = new ArrayList<>(super.getEntities());
        entities.add(tree);
        entities.add(new Castle(new Rect(120, 120)));
        entities.add(new Player(new Rect(35, 64)));
        entities.add(new Enemy(new Rect(35, 35)));
        return entities;
    }
}
