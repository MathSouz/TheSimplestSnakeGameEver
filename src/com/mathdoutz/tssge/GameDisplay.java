package com.mathdoutz.tssge;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class GameDisplay extends JPanel implements KeyListener
{
    private ScreenState screenState;

    public GameDisplay()
    {
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
        this.setCursor(blankCursor);
        this.setDoubleBuffered(true);
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(this);

        this.screenState = new GameState(this);
    }

    public void setScreenState(ScreenState screenState) {
        this.screenState = screenState;
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        screenState.render(g2);
    }

    public void tick()
    {
        screenState.tick();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        screenState.keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        screenState.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        screenState.keyReleased(e);
    }
}
