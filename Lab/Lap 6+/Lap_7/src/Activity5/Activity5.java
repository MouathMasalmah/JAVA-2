package Activity5;

import javafx.application.Application;
import javafx.stage.Stage;
// Import the javafx.scene package
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;

public class Activity5 extends Application {

	@Override
	public void start(Stage arg0) throws Exception {

		Pane pane = new Pane();

		double centerX = 200;
		double centerY = 200;
		double radius = 100;
		double Scientific = 35;
		double Literary = 55;
		double others = 10;

		Label ScientificLabel = new Label("Scientific" + Scientific + "%");
		ScientificLabel.setFont(new Font("Arial", 16));
		ScientificLabel.setTextFill(Color.BLACK);

		Label LiteraryLabel = new Label("Literary" + Literary + "%");
		LiteraryLabel.setFont(new Font("Arial", 16));
		LiteraryLabel.setTextFill(Color.BLACK);

		Label OthersLabel = new Label("Others" + others + "%");
		OthersLabel.setFont(new Font("Arial", 16));
		OthersLabel.setTextFill(Color.BLACK);
		

		Arc ScientificArc = new Arc(centerX, centerY, radius, radius, 0, 0);
		ScientificArc.setStartAngle(0.0f);
		ScientificArc.setLength(Scientific * 3.6);
		ScientificArc.setType(ArcType.ROUND);
		ScientificArc.setFill(Color.ORANGERED);

		Arc LiteraryArc = new Arc(centerX, centerY, radius, radius, 0, 0);
		LiteraryArc.setStartAngle(Scientific * 3.6);
		LiteraryArc.setLength(Literary * 3.6);
		LiteraryArc.setType(ArcType.ROUND);
		LiteraryArc.setFill(Color.LIGHTSKYBLUE);

		Arc othersArc = new Arc(centerX, centerY, radius, radius, 0, 0);
		othersArc.setStartAngle(Scientific * 3.6 + Literary * 3.6);
		othersArc.setLength(others * 3.6);
		othersArc.setType(ArcType.ROUND);
		othersArc.setFill(Color.GREEN);

		ScientificLabel.setTranslateX(250);
		ScientificLabel.setTranslateY(100);

		LiteraryLabel.setTranslateX(9);
		LiteraryLabel.setTranslateY(230);

		OthersLabel.setTranslateX(300);
		OthersLabel.setTranslateY(230);

		pane.getChildren().addAll(ScientificLabel, ScientificArc, LiteraryLabel, LiteraryArc, OthersLabel, othersArc);

		Scene scene = new Scene(pane, 400, 400);

		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Pie Chart");
		stage.setResizable(false);
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}