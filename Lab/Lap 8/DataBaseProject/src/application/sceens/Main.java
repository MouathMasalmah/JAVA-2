package application.sceens;

import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application{
	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("employee-screen.fxml"));
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("customer-screen.fxml"));
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("supplier-screen.fxml"));


			// Parent root =
			// FXMLLoader.load(getClass().getResource("view/Calculatorview.fxml"));
			Pane root = (Pane) loader.load();
			Scene scene = new Scene(root);

			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}

}
