package com.bimzygames.apegame.common;

import com.bimzygames.apegame.DIContainer;
import com.bimzygames.apegame.entities.cameras.CameraGame;
import com.bimzygames.apegame.entities.cameras.CameraLayer;
import com.bimzygames.apegame.entities.cameras.CameraUI;
import com.bimzygames.apegame.entities.cameras.ICamera;

public class CameraUtilities
{
    public static ICamera getCamera(CameraLayer layer)
    {
        switch (layer)
        {
            case Game:
                return DIContainer.getInstance().resolve(CameraGame.class);
            case UI:
                return DIContainer.getInstance().resolve(CameraUI.class);
        }

        String error = "Camera not found, with layer " + layer.toString();
        throw new TypeNotPresentException(error, new Exception(error));
    }
}
