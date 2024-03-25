package com.bimzygames.apegame.scenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.bimzygames.apegame.DIContainer;
import com.bimzygames.apegame.Installer;
import com.bimzygames.apegame.actions.IAction;
import com.bimzygames.apegame.common.Rect;
import com.bimzygames.apegame.components.renderers.MenuSortOrders;
import com.bimzygames.apegame.entities.Entity;
import com.bimzygames.apegame.entities.UI.Button;
import com.bimzygames.apegame.services.SceneLoaderService;

import java.util.ArrayList;
import java.util.List;

public class MenuScene extends Scene
{
    @Override
    public List<Entity> getEntities() {
        List<Entity> entities = new ArrayList<>(super.getEntities());
        entities.add(new Button("Play Game", new Vector2(0, 0), new Rect(400, 150), MenuSortOrders.PlayButton.getValue(), new IAction() {
            @Override
            public void execute(Object... objects) {
                DIContainer.getInstance().resolve(SceneLoaderService.class).LoadScene(new GameScene());
            }
        }));
        return entities;
    }
}
