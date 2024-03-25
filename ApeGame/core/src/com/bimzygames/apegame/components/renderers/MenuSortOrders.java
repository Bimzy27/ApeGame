package com.bimzygames.apegame.components.renderers;

public enum MenuSortOrders {
    PlayButton(0);

    private final int value;
    MenuSortOrders(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
