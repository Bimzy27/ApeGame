package com.bimzygames.apegame.components.renderers;

public enum MenuSortOrders {

    Background(0),
    Panel(50),
    PlayButton(100),
    Title(150);

    private final int value;
    MenuSortOrders(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
