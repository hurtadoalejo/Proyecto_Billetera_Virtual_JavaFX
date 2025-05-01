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
    private TextField tf_telefono;

    @FXML
    private TextField tf_direccion;

    @FXML
    private TextField tf_correo;

    @FXML
    private TextField tf_nombre;

    @FXML
    private TextField tf_id;

    @FXML
    private Button bt_registrar;

    @FXML
    private AnchorPane ap_registrarUsuario;

    @FXML
    private TextField tf_clave;

    @FXML
    void onRegistrar() {
        registrar();
    }

    @FXML
    private void cambiarVista() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource
                    ("/co/edu/uniquindio/billeteravirtual/billeteravirtualapp/LoginMenu.fxml"));
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
                    mostrarMensaje(TITULO_USUARIO_CREADO, BODY_USUARIO_CREADO, Alert.AlertType.INFORMATION);
                    limpiarCamposTexto();
                }
                else{
                    mostrarMensaje(TITULO_USUARIO_NO_CREADO, BODY_USUARIO_NO_CREADO, Alert.AlertType.ERROR);
                }
            }
            else{
                mostrarMensaje(TITULO_INCORRECTO, BODY_INCORRECTO, Alert.AlertType.WARNING);
            }
        }
        else{
            mostrarMensaje(TITULO_INCOMPLETO, BODY_INCOMPLETO, Alert.AlertType.WARNING);
        }
    }

    private boolean verificarCamposLlenos() {
        return !tf_correo.getText().isEmpty()
                && !tf_telefono.getText().isEmpty()
                && !tf_direccion.getText().isEmpty()
                && !tf_clave.getText().isEmpty()
                && !tf_id.getText().isEmpty()
                && !tf_nombre.getText().isEmpty();
    }

    private boolean verificarCamposCorrectos(){
        return isInteger(tf_clave.getText())
                && isLong(tf_telefono.getText())
                && isLong(tf_id.getText())
                && esCorreoValido(tf_correo.getText());
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
    }
}