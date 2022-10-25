package gym.Client;

import gym.Client.Controllers.LoginController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class GymClientApplication extends Application {

	@Override
	public void start(Stage stage) {
		try {
			//FXMLLoader fxmlLoader = new FXMLLoader();
			//fxmlLoader.setControllerFactory(Main.getContext()::getBean);
			//Scene scene = new Scene(fxmlLoader.load(GymClientApplication.class.getResourceAsStream("Login.fxml")), 600, 400);
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			stage.setTitle("Login");
			stage.getIcons().add(new Image("GymIcon.png"));
			stage.setScene(new Scene(root, 600, 400));
			stage.show();
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					System.exit(0);
				}
			});
		} catch (Exception ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
