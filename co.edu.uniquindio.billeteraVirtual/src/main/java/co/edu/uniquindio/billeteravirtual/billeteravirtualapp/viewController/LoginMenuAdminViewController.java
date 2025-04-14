package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.viewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller.LoginMenuAdminController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import static co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.BilleteraVirtualConstantes.*;

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
                    ("/co/edu/uniquindio/billeteravirtual/billeteravirtualapp/MenuAdmin.fxml"));
            AnchorPane menuAdmin = loader.load();
            
            SplitPane splitPane = (SplitPane) ap_principal.getParent().getParent();
            splitPane.getItems().setAll(menuAdmin);

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
        if (verificarCambosLlenos()){
            if (verificarCambosCorrectos()){
                if (loginMenuAdminController.verificarClaveAdmin(Integer.parseInt(ta_clave.getText()))){
                    cambiarVista();
                }
                else{
                    mostrarMensaje(TITULO_PASSWORD_INCORRECTA, HEADER, BODY_PASSWORD_INCORRECTA, Alert.AlertType.ERROR);
                    ta_clave.clear();
                }
            }
            else{
                mostrarMensaje(TITULO_PASSWORD_NO_VALIDA, HEADER, BODY_PASSWORD_NO_VALIDA, Alert.AlertType.WARNING);
                ta_clave.clear();
            }
        }
        else{
            mostrarMensaje(TITULO_PASSWORD_NO_RELLENA, HEADER, BODY_PASSWORD_NO_RELLENA, Alert.AlertType.WARNING);
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

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
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