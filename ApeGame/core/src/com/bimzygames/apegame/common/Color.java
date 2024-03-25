package com.bimzygames.apegame.common;

public class Color {
    public final float r;
    public final float g;
    public final float b;
    public final float a;

    public Color(float r, float g, float b, float a) //Note, these should be in a range of 0f-1f
    {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public Color(java.awt.Color color) //Note, these should be in a range of 0f-1f
    {
        final float divisor = 255f;
        this.r = (float) color.getRed() / divisor;
        this.g = (float) color.getGreen() / divisor;
        this.b = (float) color.getBlue() / divisor;
        this.a = (float) color.getAlpha() / divisor;
    }
}
