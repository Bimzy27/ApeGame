package com.bimzygames.apegame.services;

import com.bimzygames.apegame.IUpdater;
import com.bimzygames.apegame.actions.ActionExecutor;
import com.bimzygames.apegame.components.BoxCollider;

import java.util.ArrayList;
import java.util.List;

import static com.bimzygames.apegame.common.VectorUtilities.Intersects;

public class CollisionService implements IUpdater
{
    private static CollisionService instance;
    public static CollisionService getInstance() {
        if (instance == null) {
            instance = new CollisionService();
        }
        return instance;
    }

    private ArrayList<BoxCollider> colliders = new ArrayList<BoxCollider>();

    public CollisionService()
    {
    }

    public void AddCollider(BoxCollider boxCollider)
    {
        colliders.add(boxCollider);
    }

    public void RemoveCollider(BoxCollider boxCollider)
    {
        colliders.remove(boxCollider);
    }

    @Override
    public void update() {
        for (BoxCollider col1 : colliders) {
            ArrayList<BoxCollider> activeCols = col1.getActiveCollisions();

            List<BoxCollider> collisionCopy = new ArrayList<>(activeCols);

            for (BoxCollider col2 : collisionCopy) {
                if (!Intersects(col1.getPosition(), col1.getRect(), col2.getPosition(), col2.getRect())) {
                    col1.removeCollision(col2);
                    ActionExecutor.performAction(col1.onCollisionExit, col2);
                    col2.removeCollision(col1);
                    ActionExecutor.performAction(col2.onCollisionExit, col1);
                }
            }
        }

        for (int i = 0; i < colliders.stream().count(); i++) {
            for (int j = i + 1; j < colliders.stream().count(); j++)
            {
                BoxCollider col1 = colliders.get(i);
                BoxCollider col2 = colliders.get(j);

                if (col1.getActiveCollisions().contains(col2))
                {
                    continue;
                }

                if (Intersects(col1.getPosition(), col1.getRect(), col2.getPosition(), col2.getRect()))
                {
                    col1.addCollision(col2);
                    ActionExecutor.performAction(col1.onCollisionEnter, col2);
                    col2.addCollision(col1);
                    ActionExecutor.performAction(col2.onCollisionEnter, col1);
                }
            }
        }
    }
}
