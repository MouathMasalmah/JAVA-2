package application;
	
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		Circle c1 = new Circle(10);
		c1.setFill(Color.WHITE);
		c1.setStroke(Color.BLACK);
		Label ChL1 = new Label("Red");
		CheckBox Ch1 = new CheckBox();
		Ch1.setOnAction(e->{
			if(Ch1.isSelected()) {
				c1.setFill(Color.RED);
			}else {
				c1.setFill(Color.WHITE);
			}
				});
		
		Circle c2 = new Circle(10);
		c2.setFill(Color.WHITE);
		c2.setStroke(Color.BLACK);
		Label ChL2 = new Label("Yellow");
		CheckBox Ch2 = new CheckBox();
		
		Ch2.setOnAction(e->{
			if(Ch2.isSelected()) {
				c2.setFill(Color.YELLOW);
			}else {
				c2.setFill(Color.WHITE);
			}
				});
			
		
		Circle c3 = new Circle(10);
		c3.setFill(Color.WHITE);
		c3.setStroke(Color.BLACK);
		Label ChL3 = new Label("Green");
		CheckBox Ch3 = new CheckBox();
		
		Ch3.setOnAction(e->{
			if(Ch3.isSelected()) {
				c3.setFill(Color.GREEN);
			}else {
				c3.setFill(Color.WHITE);
			}
				});
			
		Rectangle R = new Rectangle(50,120);
		R.setFill(Color.WHITE);
		R.setStroke(Color.BLACK);
		
		VBox VB = new VBox(10,c1,c2,c3);
		VB.setAlignment(Pos.CENTER);
		StackPane StP = new StackPane(R,VB);
		StP.setAlignment(R, Pos.CENTER);
		StP.setAlignment(VB, Pos.CENTER);
		HBox HB = new HBox(Ch1,ChL1,Ch2,ChL2,Ch3,ChL3);
		HB.setAlignment(Pos.BOTTOM_CENTER);
		BorderPane root = new BorderPane();
		root.setCenter(StP);
		root.setBottom(HB);
			Scene scene = new Scene(root,400,400);
			primaryStage.setScene(scene);
			primaryStage.show();
			root.requestFocus();
		
	}


	
	public static void main(String[] args) {
		launch(args);
	}
}
