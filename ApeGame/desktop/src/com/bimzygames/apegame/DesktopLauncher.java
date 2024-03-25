package com.bimzygames.apegame;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import static com.bimzygames.apegame.common.Definitions.ScreenSize;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Game Window - Rename Me!");
		config.setWindowIcon(Files.FileType.Internal, "icon.png");
		config.setWindowSizeLimits(ScreenSize.width, ScreenSize.height, ScreenSize.width, ScreenSize.height);
		new Lwjgl3Application(new Main(), config);
	}
}
