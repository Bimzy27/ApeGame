package com.bimzygames.apegame.common;

public class Bounds
{
    public final int top;
    public final int bottom;
    public final int left;
    public final int right;

    public Bounds(int top, int bottom, int left, int right)
    {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }

    public Bounds(int offset)
    {
        this(offset, offset, offset, offset);
    }
}
