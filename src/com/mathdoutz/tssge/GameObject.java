package com.mathdoutz.tssge;

import java.awt.*;

public abstract class GameObject
{
    private final GameDisplay gameDisplay;
    private int x, y;

    public GameObject(GameDisplay gameDisplay, int x, int y)
    {
        this.gameDisplay = gameDisplay;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void tick()
    {

    }

    public void render(Graphics2D graphics)
    {

    }
}
