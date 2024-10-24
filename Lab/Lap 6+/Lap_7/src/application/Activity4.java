package application;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Activity4 extends Application {
	Scanner in = new Scanner(System.in);
    @Override
    public void start(Stage stage) {
    	
    	
    	double project = 20;
    	double quiz =  10;
    	double midterm = 30;
    	double Final= 40;
    	
        Pane pane = new Pane();

        Label projectLabel = new Label("Project"+project+"%");
        projectLabel.setFont(new Font("Arial", 14));		
        projectLabel.setTextFill(Color.BLACK);

        Label quizLabel = new Label("Quiz"+quiz+"%");
        quizLabel.setFont(new Font("Arial", 16));
        quizLabel.setTextFill(Color.BLACK);

        Label midtermLabel = new Label("Midterm"+midterm+"%");
        midtermLabel.setFont(new Font("Arial", 14));
        midtermLabel.setTextFill(Color.BLACK);

        Label finalLabel = new Label("Final"+Final+"%");
        finalLabel.setFont(new Font("Arial", 16));
        finalLabel.setTextFill(Color.BLACK);

        Rectangle projectBar = new Rectangle(80, project*2);
        projectBar.setFill(Color.RED);

        Rectangle quizBar = new Rectangle(80, quiz*2);
        quizBar.setFill(Color.BLUE);

        Rectangle midtermBar = new Rectangle(80, midterm*2);
        midtermBar.setFill(Color.GREEN);

        Rectangle finalBar = new Rectangle(80, Final*2);
        finalBar.setFill(Color.ORANGE);

        projectLabel.setTranslateX(60);
        projectLabel.setTranslateY(200 - project*2);

        projectBar.setTranslateX(60);
        projectBar.setTranslateY(220 - project*2); 

        quizLabel.setTranslateX(150);
        quizLabel.setTranslateY(200 - quiz*2);

        quizBar.setTranslateX(150);
        quizBar.setTranslateY(220 - quiz*2); 

        midtermLabel.setTranslateX(240);
        midtermLabel.setTranslateY(200 - midterm*2);

        midtermBar.setTranslateX(240);
        midtermBar.setTranslateY(220 - midterm*2); 
        
        finalLabel.setTranslateX(330);
        finalLabel.setTranslateY(200-Final*2);

        finalBar.setTranslateX(330);
        finalBar.setTranslateY(220-Final*2);

        pane.getChildren().addAll(projectLabel, projectBar, quizLabel, quizBar, midtermLabel, midtermBar, finalLabel,
                finalBar);
        Scene scene = new Scene(pane, 450, 400);
        stage.setScene(scene);
        stage.setTitle("Bar Chart Example");
        stage.show();
    }

	
	public static void main(String[] args) {
		launch(args);
	}
}
