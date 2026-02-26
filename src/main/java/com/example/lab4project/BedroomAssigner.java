

package com.example.lab4project;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.time.LocalDateTime;
import java.util.function.UnaryOperator;


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

    /**
     * Gives the stay duration time
     * @param month Month you wish to stay until
     * @param day Day you wish to stay until
     * @return Gives the time for 2026
     */
    public LocalDateTime dateTime (int month, int day) {
        LocalDate currentDate = LocalDate.of(2026, month, day);
        LocalTime endTime = LocalTime.of(6,0);

        return LocalDateTime.of(currentDate, endTime);
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
        Canvas canvas = new Canvas(400, 300);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();


        //Drawing a rectangle on the canvas
        graphicsContext.setFill(Color.BEIGE);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());




        //Creating pane of vbox
        VBox vBox = new VBox(10);

        //Creating pane of Hbox
        HBox hBox = new HBox(25);


        //title pane information
        Label programInformation = new Label("This program gives you a room to stay in given a stay duration of your choice." +
                "\nIf the Program doesn't run you have enter in something wrong" +
                "\nProgram can only go up to the end of 2026");
        Label nameFieldTip = new Label("Enter your name in the textbox below.");
        Label dayFieldTip = new Label("Enter the day you wish to start your below (Number between 1-31, changes based on month)");
        Label monthFieldTip = new Label("Enter the month you wish to start your below(Number between 1-12)");



        //Text field pane information
        TextField userNameField = new TextField();
        userNameField.setPromptText("Enter your Name Here");

        NumericTextField userDayField = new NumericTextField();
        userDayField.setPromptText("Enter Day Here");

        NumericTextField userMonthField = new NumericTextField();
        userMonthField.setPromptText("Enter Month Here");









        //Button submit info
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
            graphicsContext.fillText(StudentsName + " You have been assigned to room num: " + randomNum(0,100) +
                    "\nYour stay starts: " + LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES) + " Military Time" +
                    "\n And ends on: " + dateTime(userMonthField.getIntValue(), userDayField.getIntValue()) + " Military Time"
                    , 0, TextSize);
        });

        //Clear button info
        Button buttonTwo = new Button("Clear");
        buttonTwo.setOnAction(event -> {
            //Clearing the Name Field
            userNameField.clear();
            userMonthField.clear();
            userDayField.clear();
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
        vBox.getChildren().add(programInformation);
        vBox.getChildren().add(nameFieldTip);
        vBox.getChildren().add(userNameField);
        vBox.getChildren().add(dayFieldTip);
        vBox.getChildren().add(userDayField);
        vBox.getChildren().add(monthFieldTip);
        vBox.getChildren().add(userMonthField);


        //Adding objects to the BorderPane
        borderPane.setTop(vBox);
        borderPane.setBottom(canvas);
        borderPane.setCenter(hBox);


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
