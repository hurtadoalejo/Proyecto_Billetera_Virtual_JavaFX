package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.viewController;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller.EditarPerfilController;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.UsuarioDto;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import static co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.BilleteraVirtualConstantes.*;
import static co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.BilleteraVirtualConstantes.BODY_INCOMPLETO;

public class EditarPerfilViewController {

    private EditarPerfilController editarPerfilController;
    private UsuarioDto usuario;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lb_titulo;

    @FXML
    private TextField tf_telefono;

    @FXML
    private Label lb_nombre;

    @FXML
    private Button bt_actualizar;

    @FXML
    private TextField tf_correo;

    @FXML
    private TextField tf_nombre;

    @FXML
    private Label lb_telefono;

    @FXML
    private Button bt_limpiar;

    @FXML
    private Label lb_correo;

    @FXML
    private Label lb_clave;

    @FXML
    private TextField tf_clave;

    public void setUsuario(UsuarioDto usuarioDto) {
        usuario = usuarioDto;
        limpiarCampos();
    }

    @FXML
    void onLimpiar() {
        limpiarCampos();
    }

    @FXML
    void onActualizar() {
        actualizar();
    }

    private UsuarioDto crearUsuario() {
        return new UsuarioDto(tf_nombre.getText(), usuario.idUsuario(), tf_correo.getText(), tf_telefono.getText(),
                usuario.direccion(), Integer.parseInt(tf_clave.getText()));
    }

    private void actualizar() {
        if (verificarCamposLlenos()) {
            if (verificarCamposCorrectos()){
                UsuarioDto usuarioNuevo = crearUsuario();
                if (editarPerfilController.actualizarUsuario(usuario.idUsuario(), usuarioNuevo)) {
                    usuario = usuarioNuevo;
                    limpiarCampos();
                    mostrarMensaje(TITULO_USUARIO_ACTUALIZADO, HEADER, BODY_USUARIO_ACTUALIZADO, Alert.AlertType.INFORMATION);
                }
                else{
                    mostrarMensaje(TITULO_USUARIO_NO_ACTUALIZADO, HEADER, BODY_USUARIO_NO_ACTUALIZADO, Alert.AlertType.ERROR);
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

    public void limpiarCampos() {
        tf_clave.setText(String.valueOf(usuario.clave()));
        tf_correo.setText(usuario.correoElectronico());
        tf_nombre.setText(usuario.nombreCompleto());
        tf_telefono.setText(usuario.numeroTelefono());
    }

    private boolean verificarCamposLlenos() {
        if (tf_correo.getText().isEmpty() || tf_telefono.getText().isEmpty()||
                tf_clave.getText().isEmpty() || tf_nombre.getText().isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean verificarCamposCorrectos(){
        if (isInteger(tf_clave.getText()) && isLong(tf_telefono.getText()) && esCorreoValido(tf_correo.getText())) {
            return true;
        }
        return false;
    }

    public static boolean esCorreoValido(String correo) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return correo.matches(regex);
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

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    @FXML
    void initialize() {
        editarPerfilController = new EditarPerfilController();
        assert lb_titulo != null : "fx:id=\"lb_titulo\" was not injected: check your FXML file 'EditarPerfil.fxml'.";
        assert tf_telefono != null : "fx:id=\"tf_telefono\" was not injected: check your FXML file 'EditarPerfil.fxml'.";
        assert lb_nombre != null : "fx:id=\"lb_nombre\" was not injected: check your FXML file 'EditarPerfil.fxml'.";
        assert bt_actualizar != null : "fx:id=\"bt_actualizar\" was not injected: check your FXML file 'EditarPerfil.fxml'.";
        assert tf_correo != null : "fx:id=\"tf_correo\" was not injected: check your FXML file 'EditarPerfil.fxml'.";
        assert tf_nombre != null : "fx:id=\"tf_nombre\" was not injected: check your FXML file 'EditarPerfil.fxml'.";
        assert lb_telefono != null : "fx:id=\"lb_telefono\" was not injected: check your FXML file 'EditarPerfil.fxml'.";
        assert bt_limpiar != null : "fx:id=\"bt_limpiar\" was not injected: check your FXML file 'EditarPerfil.fxml'.";
        assert lb_correo != null : "fx:id=\"lb_correo\" was not injected: check your FXML file 'EditarPerfil.fxml'.";
        assert lb_clave != null : "fx:id=\"lb_clave\" was not injected: check your FXML file 'EditarPerfil.fxml'.";
        assert tf_clave != null : "fx:id=\"tf_clave\" was not injected: check your FXML file 'EditarPerfil.fxml'.";

    }
}