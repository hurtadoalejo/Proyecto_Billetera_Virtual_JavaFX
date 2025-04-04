package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.viewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller.LoginMenuAdminController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginMenuAdminViewController {

    private LoginMenuAdminController loginMenuAdminController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lb_titulo;

    @FXML
    private AnchorPane ap_principal;

    @FXML
    private TextField ta_clave;

    @FXML
    private Button bt_ingresar;

    @FXML
    private Label lb_ingresar;

    @FXML
    private void cambiarVista() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource
                    ("/co/edu/uniquindio/billeteravirtual/billeteravirtualapp/GestionUsuarios.fxml"));
            AnchorPane gestionUsuario = loader.load();

            SplitPane splitPane = (SplitPane) ap_principal.getParent().getParent();
            splitPane.getItems().setAll(gestionUsuario);

        } catch (IOException e) {
            System.err.println("Error al cambiar la vista: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void onIngresar() {
        ingresar();
    }

    private void ingresar() {
        if (verificarCambosCorrectos() && verificarCambosLlenos()){
            if (loginMenuAdminController.verificarClaveAdmin(Integer.parseInt(ta_clave.getText()))){
                cambiarVista();
            }
        }
    }

    private boolean verificarCambosLlenos(){
        if (ta_clave.getText().isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean verificarCambosCorrectos(){
        if (isInteger(ta_clave.getText())) {
            return true;
        }
        return false;
    }

    private boolean isInteger(String text){
        if (text == null || text.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @FXML
    void initialize() {
        loginMenuAdminController = new LoginMenuAdminController();
        assert lb_titulo != null : "fx:id=\"lb_titulo\" was not injected: check your FXML file 'LoginMenuAdmin.fxml'.";
        assert ap_principal != null : "fx:id=\"ap_principal\" was not injected: check your FXML file 'LoginMenuAdmin.fxml'.";
        assert ta_clave != null : "fx:id=\"ta_clave\" was not injected: check your FXML file 'LoginMenuAdmin.fxml'.";
        assert bt_ingresar != null : "fx:id=\"bt_ingresar\" was not injected: check your FXML file 'LoginMenuAdmin.fxml'.";
        assert lb_ingresar != null : "fx:id=\"lb_ingresar\" was not injected: check your FXML file 'LoginMenuAdmin.fxml'.";

    }
}