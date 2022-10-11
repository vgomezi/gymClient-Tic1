package gym.Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class GymClientApplication extends Application {

	@Override
	public void start(Stage stage) throws IOException {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setControllerFactory(Main.getContext()::getBean);
			Scene scene = new Scene(fxmlLoader.load(GymClientApplication.class.getResourceAsStream("LoginPrueba.fxml")), 320, 240);
			stage.setTitle("Login");
			stage.setScene(scene);
			stage.show();
		} catch (Exception ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
