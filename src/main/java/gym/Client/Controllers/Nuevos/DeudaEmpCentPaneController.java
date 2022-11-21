package gym.Client.Controllers.Nuevos;

import gym.Client.Classes.PagoEmpCentObject;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class DeudaEmpCentPaneController {


    @FXML
    private Label deudaLabel;

    @FXML
    private Label nombreLabel;

    @FXML
    private Label mailLabel;

    private PagoEmpCentObject pago;

    @FXML
    void onDeudaPaneClick(MouseEvent mouseEvent) {

    }

    public void setearDatos(PagoEmpCentObject pagoEmpCentObject) {
        this.pago = pagoEmpCentObject;

        nombreLabel.setText("EMPRESA:  " + String.valueOf(pagoEmpCentObject.getEmpresa().getNombre()));
        mailLabel.setText("MAIL:  " + String.valueOf(pagoEmpCentObject.getEmpresaMailPago()));
        deudaLabel.setText("DEUDA:  " + String.valueOf(pagoEmpCentObject.getMonto()));


    }
}
