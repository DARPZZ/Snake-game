package com.example.snakegamefucked;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Food
{
    private static final int MAX_VISIBLE_TIME = 7000; // in milliseconds


    private Random random = new Random();
    private int x;
    private int y;
    private int size;
    private int squareSize;
    private Timer timer;
    private TimerTask timerTask;
    private boolean isVisible;

    public boolean isSpecial()
    {
        return isSpecial;
    }

    public void setSpecial(boolean special)
    {
        isSpecial = special;
    }

    private boolean isSpecial;

    public Food(int squareSize) {
        this.squareSize = squareSize;
        size = squareSize;
        isVisible = false;
    }

    public void generate(int screen, int height, int unitSize) {
        if (isVisible) {
            cancelTimer();
        }
        isVisible = true;
        int randomNum = random.nextInt(100);
        if (randomNum <= 20) {
            isSpecial = true;
        } else {
            isSpecial = false;
        }
        x = random.nextInt(screen / unitSize) * unitSize;
        y = random.nextInt(height / unitSize) * unitSize;
        startTimer();
    }

    private void startTimer() {
        int visibleTime = MAX_VISIBLE_TIME;
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                isVisible = false;
                timer.cancel();
            }
        };
        timer.schedule(timerTask, visibleTime);
    }

    public void draw(GraphicsContext gc) {
        if (isVisible) {
            if (isSpecial)
            {
                gc.setFill(Color.BLUE);
                gc.fillRect((Math.round(x / squareSize) * squareSize), Math.round(y / squareSize) * squareSize, size, size);
            }else {
                gc.setFill(Color.MEDIUMVIOLETRED);
                gc.fillRect((Math.round(x / squareSize) * squareSize), Math.round(y / squareSize) * squareSize, size, size);
            }
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSize() {
        return size;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void cancelTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

}