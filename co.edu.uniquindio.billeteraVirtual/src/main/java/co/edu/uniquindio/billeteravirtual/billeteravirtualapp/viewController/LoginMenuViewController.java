package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.viewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller.LoginMenuController;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.UsuarioDto;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import static co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.BilleteraVirtualConstantes.*;
import static co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.MetodosReutilizables.isInteger;
import static co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.MetodosReutilizables.mostrarMensaje;

public class LoginMenuViewController {

    LoginMenuController loginMenuController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane ap_principal;

    @FXML
    private TextField ta_clave;

    @FXML
    private Button bt_ingresar;

    @FXML
    private TextField ta_id;

    @FXML
    void onIngresar() {
        ingresar();
    }

    @FXML
    void initialize() {
        loginMenuController = new LoginMenuController();
    }

    @FXML
    private void cambiarVistaUsuario(UsuarioDto usuarioDto) {
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

    @FXML
    private void cambiarVistaAdmin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource
                    ("/co/edu/uniquindio/billeteravirtual/billeteravirtualapp/MenuAdmin.fxml"));
            AnchorPane menuAdmin = loader.load();
            MenuAdminViewController viewController = loader.getController();
            SplitPane splitPane = (SplitPane) ap_principal.getParent().getParent();
            splitPane.getItems().setAll(menuAdmin);
        } catch (IOException e) {
            System.err.println("Error al cambiar la vista: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private void ingresar() {
        if (verificarCambosLlenos()){
            if (verificarCambosCorrectos()){
                String usuario = ta_id.getText();
                int clave = Integer.parseInt(ta_clave.getText());
                if (iniciarSesionUsuario(usuario, clave)) {
                    return;
                } else {
                    if (!iniciarSesionAdmin(clave)) {
                        mostrarMensaje(TITULO_CREDENCIALES_INCORRECTAS,
                                BODY_CREDENCIALES_INCORRECTAS, Alert.AlertType.WARNING);
                    }
                }
            }
            else {
                mostrarMensaje(TITULO_CREDENCIALES_NO_VALIDAS,
                        BODY_CREDENCIALES_NO_VALIDAS, Alert.AlertType.WARNING);
            }
        }
        else {
            mostrarMensaje(TITULO_CREDENCIALES_NO_RELLENAS,
                    BODY_CREDENCIALES_NO_RELLENAS, Alert.AlertType.WARNING);
        }
    }

    private boolean iniciarSesionAdmin(int clave) {
        if (loginMenuController.verificarCredencialesAdmin(clave)){
            mostrarMensaje(TITULO_BIENVENIDA, BODY_BIENVENIDA + "Administrador",
                    Alert.AlertType.INFORMATION);
            cambiarVistaAdmin();
            return true;
        }
        return false;
    }

    private boolean iniciarSesionUsuario(String usuario, int clave) {
        if (loginMenuController.verificarCredencialesUsuario(usuario, clave)){
            UsuarioDto usuarioDto = loginMenuController.obtenerUsuario(usuario);
            mostrarMensaje(TITULO_BIENVENIDA, BODY_BIENVENIDA + usuarioDto.nombreCompleto(),
                    Alert.AlertType.INFORMATION);
            cambiarVistaUsuario(usuarioDto);
            return true;
        }
        return false;
    }

    private boolean verificarCambosLlenos(){
        return !ta_clave.getText().isEmpty() && !ta_id.getText().isEmpty();
    }

    private boolean verificarCambosCorrectos(){
        return isInteger(ta_clave.getText()) && isInteger(ta_id.getText());
    }
}