package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;

public class Main extends Application {
	@Override
	public void start(Stage stage) {
		Text Name = new Text("Your Name:");
		Name.setFont(new Font(20));
		TextField TextBox1 = new TextField("");
		TextField TextBox2 = new TextField("");
		
		Text Welcome = new Text("Welcome From Java 2");
		Welcome.setFont(new Font(20));
		
		
		Button clickMe =new Button("click me") ;
		VBox V1 = new VBox(Welcome);
		
		V1.setAlignment(Pos.CENTER);
		
		
		HBox H = new HBox(20,Name, TextBox1, TextBox2,clickMe);
		H.setAlignment(Pos.CENTER);
		BorderPane pane = new BorderPane();
		
		Rectangle R1 =new Rectangle(50, 200);
		R1.setFill(Color.BLUE);
		R1.setStroke(Color.BLACK);
		Rectangle R2 =new Rectangle(50, 200);
		R2.setFill(Color.GREEN);
		R2.setStroke(Color.BLACK);
		R2.setRotate(45);
		Rectangle R3 =new Rectangle(50, 200);
		R3.setFill(Color.YELLOW);
		R3.setStroke(Color.BLACK);
		R3.setRotate(-45);
		Rectangle R4 =new Rectangle(50, 200);
		R4.setFill(Color.RED);
		R4.setRotate(90);
		R4.setStroke(Color.BLACK);
		
		StackPane pane2 = new StackPane(R4,R1,R2,R3);
		pane2.setLayoutX(250);
		pane2.setLayoutX(250);
		pane.setTop(Welcome);
		pane.setCenter(pane2);
		pane.setBottom(H);
		pane.setAlignment(Welcome, Pos.CENTER);
		Scene scene = new Scene(pane, 500, 500);
		stage.setTitle("Quiz");
		stage.setScene(scene);
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}