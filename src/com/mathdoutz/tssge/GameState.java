package com.mathdoutz.tssge;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameState extends ScreenState
{
    private final int gridCount = 20;
    private final int gridSize = 600 / gridCount;
    private int gameTime = 0;
    private int gameSpeed = 1;

    private Point snakeHead;
    private Side currentHeadSide = Side.NONE;

    private List<Point> snakeTail = new ArrayList<>();
    private int snakeTailLength = 0;

    private Point apple;
    private int score = 0;
    private Point lastSnakeSpot = null;

    public GameState(GameDisplay gameDisplay)
    {
        super(gameDisplay);
        this.snakeHead = new Point(0, 0);
        this.apple = new Point(0, 0);
        this.replaceAppleToRandomSpot();
    }

    public void replaceAppleToRandomSpot()
    {
        List<Point> availableSpots = new ArrayList<>();

        for(int x = 0; x < gridCount; x++)
        {
            for (int y = 0; y < gridCount; y++)
            {
                Point p = new Point(x, y);
                boolean canAdd = true;

                for(Point tail : snakeTail)
                {
                    if((p.x == tail.x && p.y == tail.y) || (p.x == snakeHead.x && p.y == snakeHead.y))
                    {
                        canAdd = false;
                        break;
                    }
                }

                if(canAdd)
                    availableSpots.add(p);
            }
        }

        Point randomSpot = availableSpots.get(new Random().nextInt(availableSpots.size()));

        this.apple.x = randomSpot.x;
        this.apple.y = randomSpot.y;
    }

    public void incrementSnakeLength()
    {
        snakeTail.add(new Point(this.lastSnakeSpot));
    }

    private boolean isSnakeEatingApple()
    {
        return this.snakeHead.x == this.apple.x && this.snakeHead.y == this.apple.y;
    }

    private boolean isSnakeEatingSelf()
    {
        boolean isEating = false;

        for (Point tail : snakeTail)
        {
            if(snakeHead.x == tail.x && snakeHead.y == tail.y)
            {
                isEating = true;
                break;
            }
        }

        return isEating;
    }

    @Override
    void render(Graphics2D g2)
    {
        g2.setColor(Color.WHITE);
        g2.drawRect(0, 0, getGameDisplay().getWidth() - 1, getGameDisplay().getHeight() - 1);

        //apple draw
        g2.drawRect(apple.x * gridSize, apple.y * gridSize, gridSize - 1, gridSize - 1);

        //snake tail draw

        for (int i = 0; i < snakeTail.size(); i++)
        {
            Point p = snakeTail.get(i);
            g2.fillRect(p.x * gridSize, p.y * gridSize, gridSize, gridSize);
        }

        // snake draw
        g2.fillRect(snakeHead.x * gridSize, snakeHead.y * gridSize, gridSize, gridSize);
    }

    @Override
    void tick()
    {
        if(gameTime > gameSpeed)
        {
            if(currentHeadSide != Side.NONE)
            {
                this.lastSnakeSpot = new Point(snakeHead);
                incrementSnakeLength();
            }

            snakeHead.x += currentHeadSide.xOffset;
            snakeHead.y -= currentHeadSide.yOffset;

            if(snakeHead.x < 0)
            {
                snakeHead.x = 0;
                currentHeadSide = Side.NONE;
                //this.getGameDisplay().setScreenState(new ScoreState(getGameDisplay(), this));
            }

            if(snakeHead.y < 0)
            {
                snakeHead.y = 0;
                currentHeadSide = Side.NONE;
                //this.getGameDisplay().setScreenState(new ScoreState(getGameDisplay(), this));
            }

            if(snakeHead.x > gridCount - 1)
            {
                snakeHead.x = gridCount - 1;
                currentHeadSide = Side.NONE;
                //this.getGameDisplay().setScreenState(new ScoreState(getGameDisplay(), this));
            }

            if(snakeHead.y > gridCount - 1)
            {
                snakeHead.y = gridCount - 1;
                currentHeadSide = Side.NONE;
                //this.getGameDisplay().setScreenState(new ScoreState(getGameDisplay(), this));
            }

            if(isSnakeEatingApple())
            {
                replaceAppleToRandomSpot();
                score++;
                snakeTailLength++;
            }

            if(currentHeadSide != Side.NONE)
            {
                if(this.snakeTail.size() > snakeTailLength)
                {
                    for (int i = 0; i < (this.snakeTail.size() - 1) - snakeTailLength; i++)
                    {
                        snakeTail.remove(snakeTail.get(i));
                    }
                }
            }

            if(isSnakeEatingSelf() && currentHeadSide != Side.NONE)
            {
                currentHeadSide = Side.NONE;
                this.getGameDisplay().setScreenState(new ScoreState(getGameDisplay(), this));
            }

            gameTime = 0;
        }

        else
        {
            gameTime++;
        }
    }

    public int getScore() {
        return score;
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        super.keyPressed(e);

        if(e.getKeyCode() == KeyEvent.VK_A && currentHeadSide != Side.RIGHT && currentHeadSide != Side.LEFT)
        {
            currentHeadSide = Side.LEFT;
        }

        else if(e.getKeyCode() == KeyEvent.VK_D && currentHeadSide != Side.RIGHT && currentHeadSide != Side.LEFT)
        {
            currentHeadSide = Side.RIGHT;
        }

        else if(e.getKeyCode() == KeyEvent.VK_W && currentHeadSide != Side.UP && currentHeadSide != Side.DOWN)
        {
            currentHeadSide = Side.UP;
        }

        else if(e.getKeyCode() == KeyEvent.VK_S && currentHeadSide != Side.UP && currentHeadSide != Side.DOWN)
        {
            currentHeadSide = Side.DOWN;
        }
    }

    enum Side
    {
        NONE(0, 0), LEFT(-1, 0), RIGHT(1, 0), UP(0, 1), DOWN(0, -1);

        private int xOffset, yOffset;

        Side(int xOffset, int yOffset)
        {
            this.xOffset = xOffset;
            this.yOffset = yOffset;
        }

        public int getxOffset() {
            return xOffset;
        }

        public int getyOffset() {
            return yOffset;
        }
    }
}
