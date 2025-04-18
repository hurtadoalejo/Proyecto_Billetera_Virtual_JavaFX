package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.viewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.UsuarioDto;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class MenuUsuarioViewController {

    UsuarioDto usuario;

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
        cargarVistaGestionDinero();
        cargarVistaGestionCategorias();
        cargarVistaGestionPresupuestos();
    }

    /**
     * Metodo para configurar el cambio de tab
     */
    private void configurarCambioDeTab() {
        tp_menuUsuario.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            if (newTab == tab_editarPerfil) {
                cargarVistaEditarPerfil();
            } else if (newTab == tab_gestioDinero) {
                cargarVistaGestionDinero();
            } else if (newTab == tab_cuentas) {
                cargarVistaGestionCuentasUsuario();
            } else if (newTab == tab_categorias) {
                cargarVistaGestionCategorias();
            } else if (newTab == tab_presupuestos) {
                cargarVistaGestionPresupuestos();
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
            EditarPerfilViewController viewController = loader.getController();
            viewController.setUsuario(usuario);
            viewController.setControladorPadre(this);
            sp_editarPerfil.getItems().setAll(nuevaVista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarVistaGestionPresupuestos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/co/edu/uniquindio/billeteravirtual/billeteravirtualapp/GestionPresupuestos.fxml"));
            AnchorPane nuevaVista = loader.load();
            GestionPresupuestosViewController viewController = loader.getController();
            viewController.setUsuario(usuario);
            sp_presupuestos.getItems().setAll(nuevaVista);
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

    private void cargarVistaGestionDinero() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/co/edu/uniquindio/billeteravirtual/billeteravirtualapp/GestionDinero.fxml"));
            AnchorPane nuevaVista = loader.load();
            GestionDineroViewController viewController = loader.getController();
            viewController.setUsuario(usuario);
            sp_gestionDinero.getItems().setAll(nuevaVista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarVistaGestionCategorias() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/co/edu/uniquindio/billeteravirtual/billeteravirtualapp/GestionCategorias.fxml"));
            AnchorPane nuevaVista = loader.load();
            GestionCategoriasViewController viewController = loader.getController();
            viewController.setUsuario(usuario);
            sp_categorias.getItems().setAll(nuevaVista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
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
