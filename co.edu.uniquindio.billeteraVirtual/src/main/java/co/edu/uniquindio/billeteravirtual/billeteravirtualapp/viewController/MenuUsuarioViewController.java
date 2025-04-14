package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.viewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller.MenuUsuarioController;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.UsuarioDto;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class MenuUsuarioViewController {

    UsuarioDto usuario;
    private MenuUsuarioController menuUsuarioController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Tab tab_editarPerfil;

    @FXML
    private Tab tab_categorias;

    @FXML
    private Tab tab_gestioDinero;

    @FXML
    private Tab tab_cuentas;

    @FXML
    private SplitPane sp_gestionDinero;

    @FXML
    private Tab tab_transacciones;

    @FXML
    private SplitPane sp_presupuestos;

    @FXML
    private SplitPane sp_editarPerfil;

    @FXML
    private AnchorPane ap_menuAdmin;

    @FXML
    private Tab tab_presupuestos;

    @FXML
    private SplitPane sp_transacciones;

    @FXML
    private SplitPane sp_cuentas;

    @FXML
    private TabPane tp_menuUsuario;

    @FXML
    private SplitPane sp_categorias;

    public void setUsuario(UsuarioDto usuarioDto) {
        usuario = usuarioDto;
        cargarVistaEditarPerfil();
        cargarVistaGestionCuentasUsuario();
    }

    /**
     * Metodo para configurar el cambio de tab
     */
    private void configurarCambioDeTab() {
        tp_menuUsuario.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            if (newTab == tab_editarPerfil) {
                cargarVistaEditarPerfil();
            } else if (newTab == tab_gestioDinero) {
                System.out.println();
            } else if (newTab == tab_cuentas) {
                cargarVistaGestionCuentasUsuario();
            } else if (newTab == tab_categorias) {
                System.out.println();
            } else if (newTab == tab_presupuestos) {
                System.out.println();
            } else if (newTab == tab_transacciones) {
                System.out.println();
            }
        });
    }

    private void cargarVistaEditarPerfil() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/co/edu/uniquindio/billeteravirtual/billeteravirtualapp/EditarPerfil.fxml"));
            AnchorPane nuevaVista = loader.load();
            EditarPerfilViewController editarPerfilViewController = loader.getController();
            editarPerfilViewController.setUsuario(usuario);
            editarPerfilViewController.setControladorPadre(this);
            sp_editarPerfil.getItems().setAll(nuevaVista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarVistaGestionCuentasUsuario() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/co/edu/uniquindio/billeteravirtual/billeteravirtualapp/GestionCuentasUsuario.fxml"));
            AnchorPane nuevaVista = loader.load();
            GestionCuentasUsuarioViewController viewController = loader.getController();
            viewController.setUsuario(usuario);
            sp_cuentas.getItems().setAll(nuevaVista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        menuUsuarioController = new MenuUsuarioController();
        configurarCambioDeTab();
        assert tab_editarPerfil != null : "fx:id=\"tab_editarPerfil\" was not injected: check your FXML file 'MenuUsuario.fxml'.";
        assert tab_categorias != null : "fx:id=\"tab_categorias\" was not injected: check your FXML file 'MenuUsuario.fxml'.";
        assert tab_gestioDinero != null : "fx:id=\"tab_gestioDinero\" was not injected: check your FXML file 'MenuUsuario.fxml'.";
        assert tab_cuentas != null : "fx:id=\"tab_cuentas\" was not injected: check your FXML file 'MenuUsuario.fxml'.";
        assert sp_gestionDinero != null : "fx:id=\"sp_gestionDinero\" was not injected: check your FXML file 'MenuUsuario.fxml'.";
        assert tab_transacciones != null : "fx:id=\"tab_transacciones\" was not injected: check your FXML file 'MenuUsuario.fxml'.";
        assert sp_presupuestos != null : "fx:id=\"sp_presupuestos\" was not injected: check your FXML file 'MenuUsuario.fxml'.";
        assert sp_editarPerfil != null : "fx:id=\"sp_editarPerfil\" was not injected: check your FXML file 'MenuUsuario.fxml'.";
        assert ap_menuAdmin != null : "fx:id=\"ap_menuAdmin\" was not injected: check your FXML file 'MenuUsuario.fxml'.";
        assert tab_presupuestos != null : "fx:id=\"tab_presupuestos\" was not injected: check your FXML file 'MenuUsuario.fxml'.";
        assert sp_transacciones != null : "fx:id=\"sp_transacciones\" was not injected: check your FXML file 'MenuUsuario.fxml'.";
        assert sp_cuentas != null : "fx:id=\"sp_cuentas\" was not injected: check your FXML file 'MenuUsuario.fxml'.";
        assert tp_menuUsuario != null : "fx:id=\"tp_menuUsuario\" was not injected: check your FXML file 'MenuUsuario.fxml'.";
        assert sp_categorias != null : "fx:id=\"sp_categorias\" was not injected: check your FXML file 'MenuUsuario.fxml'.";
    }
}