

package com.example.lab4project;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.io.IOException;
import java.util.Random;


public class BedroomAssigner extends Application {
    /**
     * Giving a random integer
     * @param min min value you wish to have
     * @param max Max value you wish to have
     * @return a random integer given a range of min and max
     */
    public int randomNum(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Min is larger than max");
        }

        Random random = new Random();
        return random.nextInt(min, max);
    }

    @Override
    public void start(Stage stage) throws IOException {



        //Creating variables that will be used
        int TextSize = 16;


        //Creating the borderPane
        BorderPane borderPane = new BorderPane();

        //Creating a group for all the children of pane
        Group group = new Group();

        //Canvas of the UI
        Canvas canvas = new Canvas(400, 24);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();


        //Drawing a rectangle on the canvas
        graphicsContext.setFill(Color.BEIGE);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), 24);




        //Creating pane of vbox
        VBox vBox = new VBox(25);

        //Creating pane of Hbox
        HBox hBox = new HBox(25);


        //title pane information
        Label ProgramInformation = new Label("Type your Name in the text field below." +
                "\nThen press the submit button to get your room number and at what time your room will be available." +
                "\nPress the clear button to get a new room based on name." +
                "\nPress the Close Application to quit.");



        //Text field pane information
        TextField userNameField = new TextField();
        userNameField.setPromptText("Enter your Name here");

        //Button pane information

        //Button Sumbit info
        Button buttonOne = new Button("Submit");
        buttonOne.setOnAction(event -> {
            //Clearing the Canvas
            graphicsContext.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
            graphicsContext.setFill(Color.BEIGE);
            graphicsContext.fillRect(0, 0, canvas.getWidth(), TextSize);

            //Creating the Information unto the Canvas
            String StudentsName = userNameField.getText();
            graphicsContext.setFont(new Font("Times New Romans", TextSize));
            graphicsContext.setFill(Color.BLACK);
            graphicsContext.fillText(StudentsName + " You have been assigned to room num: " + randomNum(0,100), 0, TextSize);
        });

        //Clear button info
        Button buttonTwo = new Button("Clear");
        buttonTwo.setOnAction(event -> {
            //Clearing the Name Field
            userNameField.clear();
            //Clearing the Canvas
            graphicsContext.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
            graphicsContext.setFill(Color.BEIGE);
            graphicsContext.fillRect(0, 0, canvas.getWidth(), TextSize);
        });

        //Close Application button info
        Button buttonThree = new Button("Close Application");
        buttonThree.setOnAction(event -> stage.close());



        //adding objects to Hbox
        hBox.getChildren().add(buttonOne);
        hBox.getChildren().add(buttonTwo);
        hBox.getChildren().add(buttonThree);




        //adding objects to vbox
        vBox.getChildren().add(ProgramInformation);
        vBox.getChildren().add(userNameField);


        //Adding objects to the BorderPane
        borderPane.setTop(vBox);
        borderPane.setBottom(hBox);
        borderPane.setCenter(canvas);


        //Adding objects to the group
        group.getChildren().add(borderPane);







        //Setting scene
        Scene scene = new Scene(group, 800, 600);
        scene.setFill(Color.BEIGE);

        //setting stage
        stage.setScene(scene);
        stage.setTitle("Bedroom Assigner");

        //Showing stage
        stage.show();

    }

    static void main(String[] args) {
        launch(args);
    }
}
