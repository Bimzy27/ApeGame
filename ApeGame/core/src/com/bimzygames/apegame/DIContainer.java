package com.bimzygames.apegame;

import com.bimzygames.apegame.debug.Logger;

import java.util.HashMap;
import java.util.Map;

public class DIContainer
{
    private static DIContainer instance;
    public static DIContainer getInstance() {
        if (instance == null) {
            instance = new DIContainer();
        }
        return instance;
    }

    private final Map<String, Object> resolvables = new HashMap<>();

    public <T> void register(Class<T> tClass, T instance) {
        resolvables.put(tClass.getName(), instance);
        Logger.Log("Registered: ", tClass.getName());
    }

    public <T> void unregister(Class<T> tClass) {
        resolvables.remove(tClass);
        Logger.Log("Unregister: ", tClass.getName());
    }

    public <T> T resolve(Class<T> tClass) {
        Object resolvable = resolvables.get(tClass.getName());
        if (resolvable == null) {
            throw new RuntimeException("Service not found for class: " + tClass.getName());
        }
        return tClass.cast(resolvable);
    }
}
