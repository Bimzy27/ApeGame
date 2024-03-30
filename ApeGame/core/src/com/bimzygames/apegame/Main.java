package com.bimzygames.apegame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.ScreenUtils;
import com.bimzygames.apegame.entities.Viewport;
import com.bimzygames.apegame.debug.Logger;
import com.bimzygames.apegame.scenes.IScene;
import com.bimzygames.apegame.scenes.MenuScene;
import com.bimzygames.apegame.services.CollisionService;
import com.bimzygames.apegame.services.SceneLoaderService;
import com.bimzygames.apegame.services.UpdateService;

import static com.bimzygames.apegame.common.Definitions.DefaultBackgroundColor;

public class Main extends ApplicationAdapter {

	Installer installer;
	SceneLoaderService sceneLoaderService;
	@Override
	public void create () {
		Logger.Log("Create Main");
		installer = new Installer();
		installer.BindResolvables();
		sceneLoaderService = DIContainer.getInstance().resolve(SceneLoaderService.class);
		sceneLoaderService.LoadScene(new MenuScene());
	}

	@Override
	public void render () {
		ScreenUtils.clear(DefaultBackgroundColor.r, DefaultBackgroundColor.g, DefaultBackgroundColor.b, DefaultBackgroundColor.a);

		UpdateService.getInstance().update();
		CollisionService.getInstance().update();
	}

	@Override
	public void resize(int width, int height)
	{
		DIContainer.getInstance().resolve(Viewport.class).resize(width, height);
	}

	@Override
	public void dispose () {
		sceneLoaderService.UnloadScene();
		installer.UnbindResolvables();
	}
}
