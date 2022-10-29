package gym.Client;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
			Parent root = FXMLLoader.load(getClass().getResource("/gym/Client/nuevo/MainUsuarioTodasActividades.fxml"));
			//Parent root = FXMLLoader.load(getClass().getResource("/gym/Client/Login.fxml"));
			stage.setTitle("LOGIN");
			stage.setIconified(false);
			stage.setResizable(false);
			stage.getIcons().add(new Image("FitnessIcon.png"));
			stage.setScene(new Scene(root));
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
