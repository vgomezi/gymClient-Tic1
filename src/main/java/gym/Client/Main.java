package gym.Client;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication//(exclude={DataSourceAutoConfiguration.class})
//@EnableAutoConfiguration
public class Main {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {

        Main.context = SpringApplication.run(Main.class);

        Application.launch(GymClientApplication.class, args);
    }

    public static ConfigurableApplicationContext getContext() {
        return context;
    }
}
