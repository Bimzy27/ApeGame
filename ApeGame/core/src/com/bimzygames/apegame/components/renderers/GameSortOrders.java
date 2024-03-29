package com.bimzygames.apegame.components.renderers;

public enum GameSortOrders
{
    Background(0),
    Foreground(1000),
    Castle(1500),
    Enemies(2000),
    Player(5000);

    private final int value;
    GameSortOrders(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
