package com.mathdoutz.tssge;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.*;

public class App implements Runnable
{
    private JFrame frame;
    private int gameSpeed = 20;
    private int gameTick = 0;
    private ScheduledFuture scheduler;
    private GameDisplay gameDisplay;

    public App()
    {
        gameDisplay = new GameDisplay();
        frame = new JFrame("The Simplest Snake Game Ever");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        gameDisplay.setPreferredSize(new Dimension(600, 600));
        frame.add(gameDisplay);

        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        scheduler = Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(this, 1000 / 60, 1000 / 60, TimeUnit.MILLISECONDS);
    }

    public static void main(String[] args)
    {
        new App();
    }

    @Override
    public void run()
    {
        gameDisplay.tick();
    }
}
