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
    private Tab tab_reporteFinanciero;

    @FXML
    private Tab tab_cuentas;

    @FXML
    private SplitPane sp_reporteFinanciero;

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

    @FXML
    void initialize() {
        configurarCambioDeTab();
    }

    public void setUsuario(UsuarioDto usuarioDto) {
        usuario = usuarioDto;
        cargarVistaEditarPerfil();
        cargarVistaGestionCuentasUsuario();
        cargarVistaGestionCategorias();
        cargarVistaGestionPresupuestos();
        cargarVistaGestionTransaccionesUsuario();
        cargarVistaReporteFinanciero();
    }

    private void configurarCambioDeTab() {
        tp_menuUsuario.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            if (newTab == tab_editarPerfil) {
                cargarVistaEditarPerfil();
            } else if (newTab == tab_reporteFinanciero) {
                cargarVistaReporteFinanciero();
            } else if (newTab == tab_cuentas) {
                cargarVistaGestionCuentasUsuario();
            } else if (newTab == tab_categorias) {
                cargarVistaGestionCategorias();
            } else if (newTab == tab_presupuestos) {
                cargarVistaGestionPresupuestos();
            } else if (newTab == tab_transacciones) {
                cargarVistaGestionTransaccionesUsuario();
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

    private void cargarVistaGestionTransaccionesUsuario() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/co/edu/uniquindio/billeteravirtual/billeteravirtualapp/GestionTransaccionesUsuario.fxml"));
            AnchorPane nuevaVista = loader.load();
            GestionTransaccionesUsuarioViewController viewController = loader.getController();
            viewController.setUsuario(usuario);
            sp_transacciones.getItems().setAll(nuevaVista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarVistaReporteFinanciero() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/co/edu/uniquindio/billeteravirtual/billeteravirtualapp/ReporteFinanciero.fxml"));
            AnchorPane nuevaVista = loader.load();
            ReporteFinancieroViewController viewController = loader.getController();
            viewController.setUsuario(usuario);
            sp_reporteFinanciero.getItems().setAll(nuevaVista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}