package gym.Client.Controllers.Nuevos;

import gym.Client.Classes.ActividadObject;
import gym.Client.Classes.PagoObject;
import gym.Client.Controllers.Usuario.Actividades.MyListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class DeudaEmpCentPaneController {

    @FXML
    private VBox DeudaEmpCentVbox;

    @FXML
    private Label deudaLabel;

    @FXML
    private Label nombreLabel;

    private MyListener myListener;

    private PagoObject pago;

    @FXML
    void onDeudaPaneClick(MouseEvent mouseEvent) {

    }

    public void setearDatos(PagoObject pagoObject, MyListener myListener) {
        this.myListener = myListener;
        this.pago = pagoObject;

        nombreLabel.setText("EMPRESA" + String.valueOf(pagoObject.getEmpresa()));
        deudaLabel.setText("DEUDA: " + String.valueOf(pagoObject.getMonto()));

    }
}
