package com.example.snakegamefucked;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import static com.example.snakegamefucked.SnakeGame.GAME_UNITS;
import static com.example.snakegamefucked.SnakeGame.UNITSIZE;

public class Snake
{
    char direction ='R';


    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodyParts = 4;

    public int getSnakeHeadSize()
    {
        return snakeHeadSize;
    }

    public void setSnakeHeadSize(int snakeHeadSize)
    {
        this.snakeHeadSize = snakeHeadSize;
    }

    private int snakeHeadSize;



    public void move() {
        // move the head
        for (int i = bodyParts - 1; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'U':
                y[0] = y[0] - UNITSIZE;
                break;
            case 'D':
                y[0] = y[0] + UNITSIZE;
                break;
            case 'L':
                x[0] = x[0] - UNITSIZE;
                break;
            case 'R':
                x[0] = x[0] + UNITSIZE;

                break;
        }
    }
    public void changeDirection(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.LEFT)) {
                    if (direction != 'R') {
                        direction = 'L';
                    }
                } else if (keyEvent.getCode().equals(KeyCode.RIGHT)) {
                    if (direction != 'L') {
                        direction = 'R';
                    }
                } else if (keyEvent.getCode().equals(KeyCode.UP)) {
                    if (direction != 'D') {
                        direction = 'U';
                    }
                } else if (keyEvent.getCode().equals(KeyCode.DOWN)) {
                    if (direction != 'U') {
                        direction = 'D';
                    }
                }
            }
        });
    }

    public void drawSnake(GraphicsContext gc, int SCREEN_WIDTH, int SCREEN_HEIGHT) {
        gc.clearRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        for (int i = 0; i < bodyParts; i++) {
            if (i == 0) {
                gc.setFill(Color.DARKGREEN);
                if (snakeHeadSize > 0) {
                    if (direction == 'R' || direction == 'L')
                    {
                        gc.fillRect(x[i], y[i], UNITSIZE, UNITSIZE*2);
                    } else if (direction == 'U' || direction == 'D') {
                        gc.fillRect(x[i], y[i], UNITSIZE*2, UNITSIZE);
                    }

                } else {
                    gc.fillRect(x[i], y[i], UNITSIZE, UNITSIZE);
                }
            } else {
                gc.setFill(Color.LIGHTGREEN);
                gc.fillRect(x[i], y[i], UNITSIZE, UNITSIZE);
            }
        }
    }


//region get/set
    public char getDirection()
    {
        return direction;
    }

    public void setDirection(char direction)
    {
        this.direction = direction;
    }

    public int[] getX()
    {
        return x;
    }

    public int[] getY()
    {
        return y;
    }

    public int getBodyParts()
    {
        return bodyParts;
    }

    public void setBodyParts(int bodyParts)
    {
        this.bodyParts = bodyParts;
    }
    private final int initialX = 0;
    private final int initialY = 0;
    //endregion

    public void reset()
    {
        // set the position of the snake to its initial position
        for (int i = 0; i < bodyParts; i++) {
            x[i] = initialX - i * UNITSIZE;
            y[i] = initialY;

        }
        direction = 'R';
        bodyParts = 4;
    }
}
