package application2;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main2 extends Application {
@Override
public void start(Stage primaryStage) {
	try {
		Parent root = FXMLLoader.load(this.getClass().getResource("View2.fxml"));
		Scene scene = new Scene(root,900,600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Uppgift 2");
		primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
	}
}

public static void main(String[] args) {
	launch(args);
	
}
}