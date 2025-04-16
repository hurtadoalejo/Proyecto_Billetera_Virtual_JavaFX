package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.viewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller.RegistrarUsuarioController;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.UsuarioDto;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import static co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.BilleteraVirtualConstantes.*;
import static co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.BilleteraVirtualConstantes.BODY_INCOMPLETO;
import static co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.MetodosReutilizables.esCorreoValido;
import static co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.MetodosReutilizables.mostrarMensaje;

public class RegistrarUsuarioViewController {

    RegistrarUsuarioController registrarUsuarioController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bt_ingresar;

    @FXML
    private TextField tf_correo;

    @FXML
    private Button bt_registrar;

    @FXML
    private TextField tf_telefono;

    @FXML
    private Label lb_direccion;

    @FXML
    private Label lb_titulo;

    @FXML
    private TextField tf_direccion;

    @FXML
    private Label lb_nombre;

    @FXML
    private TextField tf_nombre;

    @FXML
    private TextField tf_id;

    @FXML
    private Label lb_telefono;

    @FXML
    private Label lb_correo;

    @FXML
    private Label lb_clave;

    @FXML
    private Label lb_numeroId;

    @FXML
    private AnchorPane ap_registrarUsuario;

    @FXML
    private TextField tf_clave;

    @FXML
    void onIngresar() {
        ingresar();
    }

    @FXML
    void onRegistrar() {
        registrar();
    }

    @FXML
    private void cambiarVista() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource
                    ("/co/edu/uniquindio/billeteravirtual/billeteravirtualapp/LoginMenuUsuario.fxml"));
            AnchorPane loginUsuario = loader.load();

            SplitPane splitPane = (SplitPane) ap_registrarUsuario.getParent().getParent();
            splitPane.getItems().setAll(loginUsuario);

        } catch (IOException e) {
            System.err.println("Error al cambiar la vista: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void ingresar(){
        cambiarVista();
    }

    private UsuarioDto crearUsuario() {
        return new UsuarioDto(tf_nombre.getText(), tf_id.getText(), tf_correo.getText(), tf_telefono.getText(),
                tf_direccion.getText(), Integer.parseInt(tf_clave.getText()));
    }

    private void registrar(){
        if (verificarCamposLlenos()) {
            if (verificarCamposCorrectos()){
                UsuarioDto usuario = crearUsuario();
                if (registrarUsuarioController.agregarUsuario(usuario)) {
                    mostrarMensaje(TITULO_USUARIO_CREADO, HEADER, BODY_USUARIO_CREADO, Alert.AlertType.INFORMATION);
                    limpiarCamposTexto();
                }
                else{
                    mostrarMensaje(TITULO_USUARIO_NO_CREADO, HEADER, BODY_USUARIO_NO_CREADO, Alert.AlertType.ERROR);
                }
            }
            else{
                mostrarMensaje(TITULO_INCORRECTO, HEADER, BODY_INCORRECTO, Alert.AlertType.WARNING);
            }
        }
        else{
            mostrarMensaje(TITULO_INCOMPLETO, HEADER, BODY_INCOMPLETO, Alert.AlertType.WARNING);
        }
    }

    private boolean verificarCamposLlenos() {
        if (tf_correo.getText().isEmpty() || tf_telefono.getText().isEmpty() || tf_direccion.getText().isEmpty()
                || tf_clave.getText().isEmpty() || tf_id.getText().isEmpty() || tf_nombre.getText().isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean verificarCamposCorrectos(){
        if (isInteger(tf_clave.getText()) && isLong(tf_telefono.getText()) &&
                isLong(tf_id.getText()) && esCorreoValido(tf_correo.getText())) {
            return true;
        }
        return false;
    }

    private void limpiarCamposTexto() {
        tf_correo.clear();
        tf_telefono.clear();
        tf_direccion.clear();
        tf_clave.clear();
        tf_id.clear();
        tf_nombre.clear();
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

    private boolean isLong(String text){
        if (text == null || text.isEmpty()) {
            return false;
        }
        try {
            Long.parseLong(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @FXML
    void initialize() {
        registrarUsuarioController = new RegistrarUsuarioController();
        assert bt_ingresar != null : "fx:id=\"bt_ingresar\" was not injected: check your FXML file 'RegistrarUsuario.fxml'.";
        assert tf_correo != null : "fx:id=\"tf_correo\" was not injected: check your FXML file 'RegistrarUsuario.fxml'.";
        assert bt_registrar != null : "fx:id=\"bt_registrar\" was not injected: check your FXML file 'RegistrarUsuario.fxml'.";
        assert tf_telefono != null : "fx:id=\"tf_telefono\" was not injected: check your FXML file 'RegistrarUsuario.fxml'.";
        assert lb_direccion != null : "fx:id=\"lb_direccion\" was not injected: check your FXML file 'RegistrarUsuario.fxml'.";
        assert lb_titulo != null : "fx:id=\"lb_titulo\" was not injected: check your FXML file 'RegistrarUsuario.fxml'.";
        assert tf_direccion != null : "fx:id=\"tf_direccion\" was not injected: check your FXML file 'RegistrarUsuario.fxml'.";
        assert lb_nombre != null : "fx:id=\"lb_nombre\" was not injected: check your FXML file 'RegistrarUsuario.fxml'.";
        assert tf_nombre != null : "fx:id=\"tf_nombre\" was not injected: check your FXML file 'RegistrarUsuario.fxml'.";
        assert tf_id != null : "fx:id=\"tf_id\" was not injected: check your FXML file 'RegistrarUsuario.fxml'.";
        assert lb_telefono != null : "fx:id=\"lb_telefono\" was not injected: check your FXML file 'RegistrarUsuario.fxml'.";
        assert lb_correo != null : "fx:id=\"lb_correo\" was not injected: check your FXML file 'RegistrarUsuario.fxml'.";
        assert lb_clave != null : "fx:id=\"lb_clave\" was not injected: check your FXML file 'RegistrarUsuario.fxml'.";
        assert lb_numeroId != null : "fx:id=\"lb_numeroId\" was not injected: check your FXML file 'RegistrarUsuario.fxml'.";
        assert ap_registrarUsuario != null : "fx:id=\"ap_registrarUsuario\" was not injected: check your FXML file 'RegistrarUsuario.fxml'.";
        assert tf_clave != null : "fx:id=\"tf_clave\" was not injected: check your FXML file 'RegistrarUsuario.fxml'.";

    }
}