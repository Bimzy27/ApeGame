package com.bimzygames.apegame.simulation.models;

import com.bimzygames.apegame.simulation.Health;
import com.bimzygames.apegame.simulation.IDamageable;

public class CastleModel implements IModel, IDamageable
{
    private final Health health;

    public CastleModel()
    {
        health = new Health(10);
    }

    @Override
    public Health getHealth() {
        return health;
    }

    @Override
    public void takeDamage(int damage) {
        health.lose(damage);
    }
}
