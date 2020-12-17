package com.mathdoutz.tssge;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class ScreenState implements KeyListener
{
    private final GameDisplay gameDisplay;

    public ScreenState(GameDisplay gameDisplay)
    {
        this.gameDisplay = gameDisplay;
    }

    public GameDisplay getGameDisplay() {
        return gameDisplay;
    }

    abstract void render(Graphics2D graphics);
    abstract void tick();

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
