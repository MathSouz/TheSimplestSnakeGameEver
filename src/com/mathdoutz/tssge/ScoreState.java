package com.mathdoutz.tssge;

import java.awt.*;

public class ScoreState extends ScreenState
{
    private final GameState lastGame;

    public ScoreState(GameDisplay gameDisplay, GameState lastGame)
    {
        super(gameDisplay);
        this.lastGame = lastGame;
    }

    public GameState getLastGame() {
        return lastGame;
    }

    @Override
    void render(Graphics2D graphics)
    {
        graphics.setColor(Color.WHITE);
        graphics.drawString("Última pontuação: " + lastGame.getScore(), 20, 20);
    }

    @Override
    void tick()
    {

    }
}
