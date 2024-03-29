package com.bimzygames.apegame.components;

import com.badlogic.gdx.math.Vector2;
import com.bimzygames.apegame.DIContainer;
import com.bimzygames.apegame.common.Rect;
import com.bimzygames.apegame.common.Time;
import com.bimzygames.apegame.entities.Entity;
import com.bimzygames.apegame.services.CollisionService;

import static com.bimzygames.apegame.common.Math.Distance;
import static com.bimzygames.apegame.common.VectorUtilities.Intersects;

public class MoveTowards implements IComponent
{
    private Entity target = null;
    private float speed;
    private float stoppingDistance;
    private Transform transform;

    public MoveTowards(Entity target, float speed, float stoppingDistance)
    {
        this.target = target;
        this.speed = speed;
        this.stoppingDistance = stoppingDistance;
    }

    public MoveTowards(float speed, float stoppingDistance)
    {
        this.speed = speed;
        this.stoppingDistance = stoppingDistance;
    }

    @Override
    public void Initialize(Entity entity) {

        transform = entity.getTransform();
    }

    @Override
    public void Deinitialize() {

    }

    @Override
    public void update() {
        if (target == null)
        {
            return;
        }

        if (Distance(transform.position, target.getTransform().position) <= stoppingDistance)
        {
            return;
        }

        Vector2 direction = new Vector2(target.getTransform().position).sub(transform.position).nor();
        Vector2 movementStep = new Vector2(Time.deltaTime() * speed * direction.x, Time.deltaTime() * speed * direction.y);
        transform.translate(movementStep);
    }

    public void SetTarget(Entity target)
    {
        this.target = target;
    }
}
