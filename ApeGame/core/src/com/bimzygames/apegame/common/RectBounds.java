package com.bimzygames.apegame.common;

public class RectBounds
{
    public final Rect rect;
    public final Bounds bounds;

    public RectBounds(Rect rect, Bounds bounds)
    {
        this.rect = rect;
        this.bounds = bounds;
    }

    public Rect getBoundedRect()
    {
        return new Rect(rect.width - bounds.left - bounds.right, rect.height - bounds.top - bounds.bottom);
    }

    public int getTop()
    {
        return (rect.height / 2) - bounds.top;
    }

    public int getBottom()
    {
        return (rect.height / 2) - bounds.bottom;
    }

    public int getLeft()
    {
        return (rect.width / 2) - bounds.left;
    }

    public int getRight()
    {
        return (rect.width / 2) - bounds.right;
    }
}
