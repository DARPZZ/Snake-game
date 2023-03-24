package com.example.snakegamefucked;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Gameover
{
    Label snakeSize = new Label();
    Label score = new Label();
    Button buttonQ = new Button();
    Button buttonR = new Button();
    public Scene createGamveOverScene(Timeline timeline)
    {

        AnchorPane anchorPane = new AnchorPane();

        buttonR.setPrefWidth(100);
        buttonR.setPrefHeight(50);
        int xKordinat = 0;
        int yKordinat = 0;
        buttonR.setLayoutX(xKordinat+250);
        buttonR.setLayoutY(yKordinat+50);
        buttonR.setText("Play");


        buttonQ.setPrefWidth(100);
        buttonQ.setPrefHeight(50);
        buttonQ.setLayoutX(buttonR.getLayoutX());
        buttonQ.setLayoutY(buttonR.getLayoutY()+340);
        buttonQ.setText("Quit");



        Label gameover = new Label();
        gameover.setLayoutX(buttonR.getLayoutX()-100);
        gameover.setLayoutY(buttonR.getLayoutY()+240);
        gameover.setText("GAME OVER!");

        score.setLayoutX(buttonR.getLayoutX()-40);
        score.setLayoutY(buttonR.getLayoutY()+70);
        snakeSize.setLayoutX(buttonR.getLayoutY()+150);
        snakeSize.setLayoutY(buttonR.getLayoutY()+150);



        anchorPane.getChildren().addAll(buttonR,score,snakeSize,gameover,buttonQ);
        Scene scene = new Scene(anchorPane, 600, 600);
        anchorPane.setId("bc");
        String css = this.getClass().getResource("/basicstyle2.css").toExternalForm();
        scene.getStylesheets().add(css);
        timeline.stop();



        return scene;
    }

    public void changeSceneBack(Stage stage, Scene scene, Timeline timeline, SnakeGame snakeGame) {
        score.setText( snakeGame.getScorelabel().getText());
        snakeSize.setText("  Size: " + snakeGame.snake.getBodyParts());
        buttonR.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


              snakeGame.snake.setSnakeHeadSize(0);
                snakeGame.resgame();
                stage.setScene(scene);
                snakeGame.setScore(0);
                snakeGame.scorelabel.setText(("Score: " + snakeGame.score));
                timeline.play();
            }
        });

        buttonQ.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(1);
            }
        });
    }
}
