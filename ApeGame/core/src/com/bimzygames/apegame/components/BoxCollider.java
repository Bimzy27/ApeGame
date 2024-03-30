package com.bimzygames.apegame.components;

import com.badlogic.gdx.math.Vector2;
import com.bimzygames.apegame.actions.ActionBinding;
import com.bimzygames.apegame.actions.IAction;
import com.bimzygames.apegame.common.Rect;
import com.bimzygames.apegame.debug.Logger;
import com.bimzygames.apegame.entities.Entity;
import com.bimzygames.apegame.services.CollisionService;

import java.util.ArrayList;

public class BoxCollider implements IComponent
{
    private Rect rect;
    private Transform transform;
    private ArrayList<BoxCollider> activeCollisions = new ArrayList<>();
    public final ActionBinding onCollisionEnter = new ActionBinding();
    public final ActionBinding onCollisionExit = new ActionBinding();
    private final IAction onEnterAction;
    private final IAction onExitAction;

    public BoxCollider(Rect rect)
    {
        this.rect = rect;
        onEnterAction = null;
        onExitAction = null;
    }

    public BoxCollider(Rect rect, IAction onEnterAction, IAction onExitAction)
    {
        this.rect = rect;
        this.onEnterAction = onEnterAction;
        this.onExitAction = onExitAction;
    }

    @Override
    public void Initialize(Entity entity) {
        transform = entity.getTransform();
        CollisionService.getInstance().AddCollider(this);

        if (onEnterAction != null)
        {
            onCollisionEnter.addAction(onEnterAction);
        }
        if (onExitAction != null)
        {
            onCollisionExit.addAction(onExitAction);
        }

        onCollisionEnter.addAction(new IAction() {
            @Override
            public void execute(Object... objects) {
                Logger.Log(((BoxCollider)objects[0]).transform.getEntity().toString(), " collision enter!");
            }
        });
        onCollisionExit.addAction(new IAction() {
            @Override
            public void execute(Object... objects) {
                Logger.Log(((BoxCollider)objects[0]).transform.getEntity().toString(), " collision exit!");
            }
        });
    }

    @Override
    public void Deinitialize() {

        if (onEnterAction != null)
        {
            onCollisionEnter.removeAction(onEnterAction);
        }
        if (onExitAction != null)
        {
            onCollisionExit.removeAction(onExitAction);
        }

        CollisionService.getInstance().RemoveCollider(this);
    }

    @Override
    public void update() {

    }

    public Vector2 getPosition()
    {
        return transform.getPosition();
    }

    public Rect getRect()
    {
        return rect;
    }

    public boolean isColliding()
    {
        return activeCollisions.stream().count() > 0;
    }

    public Transform getTransform()
    {
        return transform;
    }

    public void addCollision(BoxCollider collider)
    {
        activeCollisions.add(collider);
    }

    public void removeCollision(BoxCollider collider)
    {
        activeCollisions.remove(collider);
    }

    public ArrayList<BoxCollider> getActiveCollisions() {
        return activeCollisions;
    }
}
