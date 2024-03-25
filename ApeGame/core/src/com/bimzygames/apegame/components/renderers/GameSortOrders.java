package com.bimzygames.apegame.components.renderers;

public enum GameSortOrders {

    // Game Camera
    Backgroud(0),
    Foreground(1000),
    Player(2000),

    //UI Camera
    Healthbar(0);


    private final int value;
    GameSortOrders(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
