package com.example.snakegamefucked;

import com.example.snakegamefucked.Database.ProjectSnake;
import com.example.snakegamefucked.Database.SnakeImpl;
import com.example.snakegamefucked.Database.SnakeTable;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;

public class Gameover
{
    TableView<SnakeTable> tableView = new TableView<>();

    Label snakeSize = new Label();
    Label score = new Label();
    Button buttonQ = new Button();
    Button buttonR = new Button();
    TextField textFieldNavn = new TextField();
    Label confirm = new Label("Enter to submit");
    Label gameover = new Label();

    ProjectSnake pd = new SnakeImpl();

    public Scene createGamveOverScene(Timeline timeline)
    {
        snakeSize.setId("label");
        score.setId("label");
        gameover.setId("label");



        AnchorPane anchorPane = new AnchorPane();

        buttonR.setPrefWidth(100);
        buttonR.setPrefHeight(50);
        int xKordinat = 0;
        int yKordinat = 0;
        buttonR.setLayoutX(xKordinat+250);
        buttonR.setLayoutY(yKordinat+20);
        buttonR.setText("Play");

        textFieldNavn.setDisable(false);
        textFieldNavn.setEditable(true);

        buttonQ.setPrefWidth(100);
        buttonQ.setPrefHeight(50);
        buttonQ.setLayoutX(buttonR.getLayoutX());
        buttonQ.setLayoutY(buttonR.getLayoutY()+340);
        buttonQ.setText("Quit");
        textFieldNavn.setLayoutX(buttonQ.getLayoutX()-25);
        textFieldNavn.setPromptText("Indstast navn");
        textFieldNavn.setLayoutY(buttonQ.getLayoutY()+80);
        confirm.setLayoutX(textFieldNavn.getLayoutX()+150);
        confirm.setLayoutY(textFieldNavn.getLayoutY());
        confirm.setId("confirm");

        tableView.setLayoutX(textFieldNavn.getLayoutX()-100);
        tableView.setLayoutY(textFieldNavn.getLayoutY()+75);
        tableView.setMaxHeight(200);
        tableView.setPrefWidth(350);



        gameover.setLayoutX(buttonR.getLayoutX()-100);
        gameover.setLayoutY(buttonR.getLayoutY()+240);
        gameover.setText("GAME OVER!");

        score.setLayoutX(buttonR.getLayoutX()-40);
        score.setLayoutY(buttonR.getLayoutY()+70);
        snakeSize.setLayoutX(buttonR.getLayoutY()+150);
        snakeSize.setLayoutY(buttonR.getLayoutY()+150);
        CreateTable();





        anchorPane.getChildren().addAll(buttonR,score,snakeSize,gameover,buttonQ,textFieldNavn,confirm,tableView);
        Scene scene = new Scene(anchorPane, 600, 900);
        anchorPane.setId("bc");
        String css = this.getClass().getResource("/basicstyle2.css").toExternalForm();
        scene.getStylesheets().add(css);
        timeline.stop();



        return scene;
    }

    public void changeSceneBack(Stage stage, Scene scene, Timeline timeline, SnakeGame snakeGame) {
        getNameForL(snakeGame);
        score.setText( snakeGame.getScorelabel().getText());
        snakeSize.setText("  Size: " + snakeGame.snake.getBodyParts());
        buttonR.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                tableView.getColumns().clear();
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
    public void getNameForL(SnakeGame snakeGame )
    {
        textFieldNavn.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent keyEvent)
            {
                if (keyEvent.getCode().equals(KeyCode.ENTER))
                {
                    String navn = textFieldNavn.getText();
                    SnakeTable ko = new SnakeTable(navn,snakeGame.snake.getBodyParts(), snakeGame.getScore());
                    try {

                        pd.addProject(ko);

                    }catch (Exception e)
                    {
                        System.out.println(e);
                    }
                    textFieldNavn.setText("");
                    populateTable();
                    textFieldNavn.setDisable(true);
                    textFieldNavn.setEditable(false);
                }

            }
        });

    }
    public void CreateTable()
    {
        TableColumn<SnakeTable, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        nameColumn.getStyleClass().add("myColumn");
        nameColumn.setPrefWidth(230);

        TableColumn<SnakeTable, Integer> scoreColumn = new TableColumn<>("Score");
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("Score"));
        scoreColumn.getStyleClass().add("myColumn");
        scoreColumn.setPrefWidth(50);

        TableColumn<SnakeTable, Integer> bodyPartsColumn = new TableColumn<>("Size");
        bodyPartsColumn.setCellValueFactory(new PropertyValueFactory<>("bodyparts"));
        bodyPartsColumn.getStyleClass().add("myColumn");
        bodyPartsColumn.setPrefWidth(50);

        tableView.getColumns().addAll(nameColumn, scoreColumn, bodyPartsColumn);
            populateTable();

    }
    public void populateTable()
    {
        try {
            ArrayList<SnakeTable> data = pd.getAll();

            ObservableList<SnakeTable> observableData = FXCollections.observableArrayList(data);
            tableView.setId("myColumn");
            tableView.setItems(observableData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
