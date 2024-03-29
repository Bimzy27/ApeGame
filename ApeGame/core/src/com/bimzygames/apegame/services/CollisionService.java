package com.bimzygames.apegame.services;

import com.bimzygames.apegame.DIContainer;
import com.bimzygames.apegame.IUpdater;
import com.bimzygames.apegame.components.BoxCollider;

import java.util.ArrayList;

import static com.bimzygames.apegame.common.VectorUtilities.Intersects;

public class CollisionService implements IUpdater
{
    private ArrayList<BoxCollider> colliders = new ArrayList<BoxCollider>();

    public CollisionService()
    {
        UpdateService.getInstance().AddUpdater(this);
    }

    public void AddCollider(BoxCollider boxCollider)
    {
        colliders.add(boxCollider);
    }

    public void RemoveCollider(BoxCollider boxCollider)
    {
        colliders.remove(boxCollider);
    }

    public ArrayList<BoxCollider> getColliders()
    {
        return colliders;
    }

    @Override
    public void update() {
        for (int i = 0; i < colliders.stream().count(); i++) {
            colliders.get(i).ClearCollisions();
        }

        for (int i = 0; i < colliders.stream().count(); i++) {
            for (int j = i + 1; j < colliders.stream().count(); j++)
            {
                BoxCollider col1 = colliders.get(i);
                BoxCollider col2 = colliders.get(j);
                if (Intersects(col1.getPosition(), col1.getRect(), col2.getPosition(), col2.getRect()))
                {
                    col1.AddCollision(col2);
                    col2.AddCollision(col1);
                }
            }
        }
    }
}
