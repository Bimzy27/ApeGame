package com.bimzygames.apegame.components;

import com.badlogic.gdx.math.Vector2;
import com.bimzygames.apegame.DIContainer;
import com.bimzygames.apegame.common.Rect;
import com.bimzygames.apegame.entities.Entity;
import com.bimzygames.apegame.services.CollisionService;

import java.util.ArrayList;

public class BoxCollider implements IComponent
{
    private Rect rect;
    private Transform transform;

    private ArrayList<BoxCollider> activeCollisions = new ArrayList<>();

    public BoxCollider(Rect rect)
    {
        this.rect = rect;
    }

    @Override
    public void Initialize(Entity entity) {
        transform = entity.getTransform();
        DIContainer.getInstance().resolve(CollisionService.class).AddCollider(this);
    }

    @Override
    public void Deinitialize() {
        DIContainer.getInstance().resolve(CollisionService.class).RemoveCollider(this);
    }

    @Override
    public void update() {

    }

    public Vector2 getPosition()
    {
        return transform.position;
    }

    public Rect getRect()
    {
        return rect;
    }

    public boolean isColliding()
    {
        return activeCollisions.stream().count() > 0;
    }

    public void ClearCollisions()
    {
        activeCollisions.clear();
    }

    public void AddCollision(BoxCollider collider)
    {
        activeCollisions.add(collider);
    }

    public ArrayList<BoxCollider> getActiveCollisions() {
        return activeCollisions;
    }
}
