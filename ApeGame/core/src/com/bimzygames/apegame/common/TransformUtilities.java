package com.bimzygames.apegame.common;

import com.badlogic.gdx.math.Vector2;
import com.bimzygames.apegame.components.Transform;

public class TransformUtilities
{
    public static Transform getRootParent(Transform transform)
    {
        Transform rootTransform = null;
        while(transform != null)
        {
            rootTransform = new Transform(transform.getEntity(), transform);
            transform = transform.getParent();
        }
        return rootTransform;
    }

    public static Vector2 getRootPosition(Transform transform)
    {
        Vector2 rootPos = new Vector2();
        while(transform != null)
        {
            rootPos.add(transform.getPosition());
            transform = transform.getParent();
        }
        return rootPos;
    }
}
