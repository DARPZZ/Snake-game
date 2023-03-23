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
    Button buttonQ = new Button();
    Button buttonR = new Button();
    public Scene createGamveOverScene(Timeline timeline)
    {

        AnchorPane anchorPane = new AnchorPane();

        buttonR.setPrefWidth(100);
        buttonR.setPrefHeight(50);
        buttonR.setLayoutX(250);
        buttonR.setLayoutY(550);
        buttonR.setText("Reset");

        buttonQ.setPrefWidth(100);
        buttonQ.setPrefHeight(50);
        buttonQ.setLayoutX(250);
        buttonQ.setLayoutY(50);
        buttonQ.setText("Quit");

        Label gameover = new Label();
        gameover.setLayoutX(150);
        gameover.setLayoutY(buttonR.getLayoutY()/2);
        gameover.setText("GAME OVER!");

        anchorPane.getChildren().addAll(buttonQ,buttonR,gameover);
        Scene scene = new Scene(anchorPane, 600, 600);
        anchorPane.setId("bc");
        String css = this.getClass().getResource("/basicstyle2.css").toExternalForm();
        scene.getStylesheets().add(css);
        timeline.stop();



        return scene;
    }

    public void changeSceneBack(Stage stage, Scene scene, Timeline timeline, SnakeGame snakeGame) {

        buttonR.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                snakeGame.resgame();
                stage.setScene(scene);
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
