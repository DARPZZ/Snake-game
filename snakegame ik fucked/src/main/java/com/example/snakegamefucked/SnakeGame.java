package com.example.snakegamefucked;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.SortedMap;
import java.util.Timer;

public class SnakeGame extends Application
{
    public Stage stage;
    Gameover gameover = new Gameover();
    private GraphicsContext gc;
    Rotate rotate = new Rotate();
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNITSIZE = 25;
    Timer timer = new Timer();
    public int speed;

    static final int GAME_UNITS = SCREEN_WIDTH * SCREEN_HEIGHT / UNITSIZE;


    boolean running = false;
    Food food = new Food(UNITSIZE);

    public Snake getSnake()
    {
        return snake;
    }

    Snake snake = new Snake();

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public int score = 0;
    Label bodySize = new Label();
    Canvas canvas = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
    BorderPane borderPane = new BorderPane();


    Label scorelabel = new Label();
    AnchorPane anchorPane = new AnchorPane();
    Scene scene = new Scene(borderPane, SCREEN_WIDTH, SCREEN_HEIGHT + 25);

    @Override
    public void start(Stage stage) throws IOException
    {
        anchorPane.setPrefHeight(25);
        String css = this.getClass().getResource("/basicstyle.css").toExternalForm();
        scene.getStylesheets().add(css);
        this.stage = stage;
        running = true;
        stage.setResizable(false);
        anchorPane.getChildren().addAll(bodySize, scorelabel);
        scorelabel.setLayoutX(150);
        borderPane.getTop();
        gc = canvas.getGraphicsContext2D();
        borderPane.setTop(anchorPane);
        borderPane.setCenter(canvas);
        draw(gc);
        startGame();
        bodySize.setText("Body size: " + snake.getBodyParts());
        snake.changeDirection(scene);
        food.generate(SCREEN_WIDTH, SCREEN_HEIGHT, UNITSIZE);

        stage.setTitle("Snake");
        stage.setScene(scene);
        stage.show();
    }

    public void draw(GraphicsContext gc)
    {
        for (int i = 0; i < SCREEN_HEIGHT / UNITSIZE; i++) {
            gc.strokeLine(i * UNITSIZE, 0, i * UNITSIZE, SCREEN_HEIGHT);
            gc.strokeLine(0, i * UNITSIZE, SCREEN_WIDTH, i * UNITSIZE);
        }
    }

    Timeline timeline = new Timeline();

    public void startGame()
    {
        timeline = new Timeline(new KeyFrame(Duration.millis(100), event ->
        {
            running = true;
            snake.move();

            snake.drawSnake(gc, SCREEN_WIDTH, SCREEN_HEIGHT);
            draw(gc);
            food.draw(gc);
            checkSelfHit();
            checkWall();
            insaneMode();
            checkFood();



            scorelabel.setText("SCORE: " + getScore());
            if (food.isVisible() == false) {
                setScore(getScore() - 10);
                food.generate(SCREEN_WIDTH, SCREEN_HEIGHT, UNITSIZE);
            }
            if (running == false) {
                Scene creategameover = gameover.createGamveOverScene(timeline);
                stage.setScene(creategameover);
                stage.show();
                running = true;
                gameover.changeSceneBack(stage, scene, timeline, this);

            }



        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void updateTimeline()
    {
        timeline.stop();// stop the timeline
        timeline.getKeyFrames().set(0, new KeyFrame(Duration.millis(speed), event ->
        {
            // update the timeline with the new speed
            snake.move();
            snake.drawSnake(gc, SCREEN_WIDTH, SCREEN_HEIGHT);

            draw(gc);
            food.draw(gc);
            checkSelfHit();
            checkWall();
            insaneMode();
            checkFood();


            if (food.isVisible() == false) {
                setScore(getScore() - 10);
                scorelabel.setText("Score: " + score);
                food.generate(SCREEN_WIDTH, SCREEN_HEIGHT, UNITSIZE);

            }
            if (running == false) {

                Scene creategameover = gameover.createGamveOverScene(timeline);
                stage.setScene(creategameover);
                stage.show();
                running= true;
                gameover.changeSceneBack(stage, scene, timeline, this);

            }

        }));
        timeline.play();
        food.generate(SCREEN_WIDTH, SCREEN_HEIGHT, UNITSIZE);
        bodySize.setText("Body size: " + snake.getBodyParts());
        setScore(getScore() + 5);
        scorelabel.setText("Score: " + score);

    }

    public void checkWall()
    {
        // Check if snake hits the wall
        if (snake.getX()[0] < 0 || snake.getX()[0] >= SCREEN_WIDTH || snake.getY()[0] < 0 || snake.getY()[0] >= SCREEN_HEIGHT) {
            running = false;
        }
    }

    public void checkSelfHit()
    {
        for (int i = snake.getBodyParts(); i > 0; i--) {
            if (snake.getX()[0] == 0 && snake.getY()[0] == 0) {

                running = true;

            } else if ((snake.getX()[0] == snake.getX()[i] && snake.getY()[0] == snake.getY()[i])) {
                running = false;
            }
        }
    }

    public void checkFood()
    {
        if (snake.getSnakeHeadSize() > 0)
        {
            checkHead();
        }

        if (snake.getX()[0] == food.getX() && snake.getY()[0] == food.getY()) {
            if (food.isDouble()) {
                snake.setSnakeHeadSize(snake.getSnakeHeadSize() + 1);

            } else {
                snake.setSnakeHeadSize(0);
            }
            if (food.isSpecial()) {
                speed = 50;
                updateTimeline();
            } else {
                speed = 100;
                updateTimeline();
            }
            snake.setBodyParts(snake.getBodyParts() + 1);

            }
        }




    public void insaneMode()
    {

        if (snake.getBodyParts() % 12 == 0) {
            canvas.setRotate(180);

        } else if (snake.getBodyParts() % 12 != 0) {
            canvas.setRotate(0);
        }
    }

    public Label getScorelabel()
    {
        return scorelabel;
    }

    public void setScorelabel(int score)
    {
        this.scorelabel.setText(String.valueOf(score));
    }

    public void resgame()
    {
        food.generate(SCREEN_WIDTH, SCREEN_HEIGHT, UNITSIZE);
        score = 0;
        scorelabel.setText(("Score: " + score));
        snake.reset();
        bodySize.setText(("Body size: " + snake.getBodyParts()));
        speed = 100;
        updateTimeline();

    }


    public static void main(String[] args)
    {
        launch();
    }
    public void checkHead()
    {
        if(snake.getX()[0]+25== food.getX() && snake.getY()[0]+25 == food.getY())
        {
            snake.setBodyParts(snake.getBodyParts() + 1);
            System.out.println("Hej");
            updateTimeline();
        }
    }
}