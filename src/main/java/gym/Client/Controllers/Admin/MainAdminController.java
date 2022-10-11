package gym.Client.Controllers.Admin;

import javafx.fxml.FXML;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


@Controller
public class MainAdminController {

    @FXML
    private Button administrarEmpresasBoton;

    @FXML
    private Button administrarCentrosBoton;

    @FXML
    protected void onAdministrarEmpresasButtonClick() {}

    @FXML
    protected void onAdministrarCentrosButtonClick() {}


}