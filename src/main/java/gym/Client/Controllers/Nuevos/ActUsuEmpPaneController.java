package gym.Client.Controllers.Nuevos;

import gym.Client.Classes.ActividadObject;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ActUsuEmpPaneController {

    @FXML
    private VBox ActUsuEmpVbox;

    @FXML
    private Label costoLabel;

    @FXML
    private Label diaLabel;

    @FXML
    private Label horaLabel;

    @FXML
    private Label nombreLabel;


    public void setearDatos(ActividadObject actividadObject) {

        nombreLabel.setText("NOMBRE:  " + actividadObject.getNombre());
        costoLabel.setText("COSTO:  " + actividadObject.getCosto());
        diaLabel.setText("DIA:  " + actividadObject.getDia().toString());
        horaLabel.setText("HORA:  " + actividadObject.getHora().toString());

    }

}
