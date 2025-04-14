package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.viewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller.LoginMenuUsuarioController;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.UsuarioDto;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import static co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.BilleteraVirtualConstantes.*;

public class LoginMenuUsuarioViewController {

    LoginMenuUsuarioController loginMenuUsuarioController;

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
    private Label lb_id;

    @FXML
    private TextField ta_id;

    @FXML
    private Label lb_clave;

    @FXML
    private Button bt_registrar;

    @FXML
    void onIngresar() {
        ingresar();
    }

    @FXML
    void onRegistrar() {
        cambiarVistaRegistrar();
    }

    @FXML
    private void cambiarVistaRegistrar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource
                    ("/co/edu/uniquindio/billeteravirtual/billeteravirtualapp/RegistrarUsuario.fxml"));
            AnchorPane registrarUsuario = loader.load();

            SplitPane splitPane = (SplitPane) ap_principal.getParent().getParent();
            splitPane.getItems().setAll(registrarUsuario);

        } catch (IOException e) {
            System.err.println("Error al cambiar la vista: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void cambiarVistaLogeado(UsuarioDto usuarioDto) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource
                    ("/co/edu/uniquindio/billeteravirtual/billeteravirtualapp/MenuUsuario.fxml"));
            AnchorPane menuUsuario = loader.load();
            MenuUsuarioViewController menuUsuarioViewController = loader.getController();
            menuUsuarioViewController.setUsuario(usuarioDto);

            SplitPane splitPane = (SplitPane) ap_principal.getParent().getParent();
            splitPane.getItems().setAll(menuUsuario);
        } catch (IOException e) {
            System.err.println("Error al cambiar la vista: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Metodo para mostrar un mensaje personalizado
     * @param titulo Titulo del mensaje
     * @param header Encabezado del mensaje
     * @param contenido Contenido del mensaje
     * @param alertType Tipo de alerta del mensaje
     */
    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    /**
     * Metodo para intercambiar la vista actual por otra si se administra un id y una clave correcta
     */
    private void ingresar() {
        if (verificarCambosLlenos()){
            if (verificarCambosCorrectos()){
                if (loginMenuUsuarioController.verificarCredencialesUsuario(ta_id.getText(), Integer.parseInt(ta_clave.getText()))){
                    UsuarioDto usuarioDto = loginMenuUsuarioController.obtenerUsuario(ta_id.getText());
                    mostrarMensaje(TITULO_BIENVENIDA,HEADER,
                            BODY_BIENVENIDA + usuarioDto.nombreCompleto(),
                            Alert.AlertType.INFORMATION);
                    cambiarVistaLogeado(usuarioDto);
                }
                else{
                    mostrarMensaje(TITULO_CREDENCIALES_INCORRECTAS, HEADER, BODY_CREDENCIALES_INCORRECTAS, Alert.AlertType.WARNING);
                    ta_clave.clear();
                    ta_id.clear();
                }
            }
            else {
                mostrarMensaje(TITULO_CREDENCIALES_NO_VALIDAS, HEADER, BODY_CREDENCIALES_NO_VALIDAS, Alert.AlertType.WARNING);
                ta_clave.clear();
                ta_id.clear();
            }
        }
        else {
            mostrarMensaje(TITULO_CREDENCIALES_NO_RELLENAS, HEADER, BODY_CREDENCIALES_NO_RELLENAS, Alert.AlertType.WARNING);
        }
    }

    /**
     * Metodo para verificar que todos los campos de texto esten rellenos
     * @return Booleano sobre si todos los campos cumplen con la condicion
     */
    private boolean verificarCambosLlenos(){
        if (ta_clave.getText().isEmpty() || ta_id.getText().isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Metodo para verificar que los campos que requieren datos diferentes de String esten rellenos con datos validos
     * @return Booleano sobre si todos los campos de este tipo cumplen con la condicion dada
     */
    private boolean verificarCambosCorrectos(){
        if (isInteger(ta_clave.getText()) && isInteger(ta_id.getText())) {
            return true;
        }
        return false;
    }

    /**
     * Metodo para verificar si un texto es un dato de tipo Integer
     * @param text Texto de tipo String
     * @return Booleano sobre si cumpleo o no con la condicion
     */
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

    /**
     * Metodo para inicializar el LoginMenuUsuarioViewController
     */
    @FXML
    void initialize() {
        loginMenuUsuarioController = new LoginMenuUsuarioController();
        assert lb_titulo != null : "fx:id=\"lb_titulo\" was not injected: check your FXML file 'LoginMenuUsuario.fxml'.";
        assert ap_principal != null : "fx:id=\"ap_principal\" was not injected: check your FXML file 'LoginMenuUsuario.fxml'.";
        assert ta_clave != null : "fx:id=\"ta_clave\" was not injected: check your FXML file 'LoginMenuUsuario.fxml'.";
        assert bt_ingresar != null : "fx:id=\"bt_ingresar\" was not injected: check your FXML file 'LoginMenuUsuario.fxml'.";
        assert lb_id != null : "fx:id=\"lb_id\" was not injected: check your FXML file 'LoginMenuUsuario.fxml'.";
        assert ta_id != null : "fx:id=\"ta_id\" was not injected: check your FXML file 'LoginMenuUsuario.fxml'.";
        assert lb_clave != null : "fx:id=\"lb_clave\" was not injected: check your FXML file 'LoginMenuUsuario.fxml'.";
        assert bt_registrar != null : "fx:id=\"bt_registrar\" was not injected: check your FXML file 'LoginMenuUsuario.fxml'.";
    }
}