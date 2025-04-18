package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.viewController;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller.GestionCuentasController;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.CuentaDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.TipoCuenta;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import static co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.BilleteraVirtualConstantes.*;
import static co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.MetodosReutilizables.*;

public class GestionCuentasViewController {

    private GestionCuentasController gestionCuentasController;
    ObservableList<CuentaDto> listaCuentas = FXCollections.observableArrayList();
    CuentaDto cuentaSeleccionada;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cb_usuarioAsociado;

    @FXML
    private Button bt_eliminar;

    @FXML
    private Label lb_nombreBanco;

    @FXML
    private TableColumn<CuentaDto, String> cl_numeroCuenta;

    @FXML
    private TextField tf_nombreBanco;

    @FXML
    private Button bt_actualizar;

    @FXML
    private TableView<CuentaDto> tb_cuentas;

    @FXML
    private TableColumn<CuentaDto, String> cl_nombreBanco;

    @FXML
    private TableColumn<CuentaDto, Double> cl_saldo;

    @FXML
    private Label lb_numeroCuenta;

    @FXML
    private TableColumn<CuentaDto, Integer> cl_idCuenta;

    @FXML
    private TextField tf_numeroCuenta;

    @FXML
    private ComboBox<TipoCuenta> cb_tipoCuenta;

    @FXML
    private Button bt_nuevo;

    @FXML
    private Label lb_tipoCuenta;

    @FXML
    private Label lb_titulo;

    @FXML
    private Label lb_idCuenta;

    @FXML
    private TableColumn<CuentaDto, String> cl_tipoCuenta;

    @FXML
    private Label lb_usuarioAsociado;

    @FXML
    private TextField tf_idCuenta;

    @FXML
    private TableColumn<CuentaDto, String> cl_usuarioAsociado;

    @FXML
    private Button bt_limpiar;

    @FXML
    void onNuevo() {
        agregarCuenta();
    }

    @FXML
    void onActualizar() {
        actualizarCuenta();
    }

    @FXML
    void onLimpiar() {
        limpiarSeleccion();
    }

    @FXML
    void onEliminar() {
        eliminarCuenta();
    }

    private CuentaDto crearCuenta() {
        return new CuentaDto(Integer.parseInt(tf_idCuenta.getText()), tf_nombreBanco.getText(),
                tf_numeroCuenta.getText(), cb_usuarioAsociado.getValue(), cb_tipoCuenta.getValue(), 0);
    }

    private CuentaDto crearCuentaConSaldo(Double saldo) {
        return new CuentaDto(Integer.parseInt(tf_idCuenta.getText()), tf_nombreBanco.getText(),
                tf_numeroCuenta.getText(), cb_usuarioAsociado.getValue(), cb_tipoCuenta.getValue(), saldo);
    }

    private void agregarCuenta() {
        if (verificarCamposLlenos()) {
            if (verificarCamposCorrectos()) {
                CuentaDto cuenta = crearCuenta();
                if (gestionCuentasController.agregarCuenta(cuenta)) {
                    listaCuentas.add(cuenta);
                    tb_cuentas.refresh();
                    limpiarSeleccion();
                    mostrarMensaje(TITULO_CUENTA_AGREGADA, BODY_CUENTA_AGREGADA, Alert.AlertType.INFORMATION);
                }
                else{
                    mostrarMensaje(TITULO_CUENTA_NO_AGREGADA, BODY_CUENTA_NO_AGREGADA, Alert.AlertType.ERROR);
                }
            }
            else{
                mostrarMensaje(TITULO_INCORRECTO, BODY_INCORRECTO, Alert.AlertType.WARNING);
            }
        }
        else {
            mostrarMensaje(TITULO_INCOMPLETO, BODY_INCOMPLETO, Alert.AlertType.WARNING);
        }
    }

    private void actualizarCuenta() {
        if (cuentaSeleccionada != null) {
            if (verificarCamposLlenos()) {
                if (verificarCamposCorrectos()) {
                    CuentaDto cuentaNueva = crearCuentaConSaldo(cuentaSeleccionada.saldo());
                    if (gestionCuentasController.actualizarCuenta(cuentaSeleccionada, cuentaNueva)) {
                        intercambiarCuentas(cuentaSeleccionada.idCuenta(), cuentaNueva);
                        limpiarSeleccion();
                        tb_cuentas.refresh();
                        mostrarMensaje(TITULO_CUENTA_ACTUALIZADA, BODY_CUENTA_ACTUALIZADA, Alert.AlertType.INFORMATION);
                    }
                    else {
                        mostrarMensajeNoActualizarCuenta(cuentaSeleccionada);
                    }
                }
                else {
                    mostrarMensaje(TITULO_INCORRECTO, BODY_INCORRECTO, Alert.AlertType.WARNING);
                }
            }
            else {
                mostrarMensaje(TITULO_INCOMPLETO, BODY_INCOMPLETO, Alert.AlertType.WARNING);
            }
        }
        else {
            mostrarMensaje(TITULO_CUENTA_NO_SELECCIONADA, BODY_CUENTA_NO_SELECCIONADA, Alert.AlertType.WARNING);
        }
    }

    private void mostrarMensajeNoActualizarCuenta(CuentaDto cuenta) {
        int idCuenta = Integer.parseInt(tf_idCuenta.getText());
        String numCuenta = tf_numeroCuenta.getText();
        if (gestionCuentasController.verificarCuentaId(idCuenta)
        && cuenta.idCuenta() != idCuenta) {
            mostrarMensaje(TITULO_CUENTA_NO_ACTUALIZADA, BODY_CUENTA_NO_ACTUALIZADA_ID, Alert.AlertType.ERROR);
        }
        if (gestionCuentasController.verificarCuentaNumCuenta(numCuenta)
                && !cuenta.numCuenta().equals(numCuenta)) {
            mostrarMensaje(TITULO_CUENTA_NO_ACTUALIZADA, BODY_CUENTA_NO_ACTUALIZADA_NUM_CUENTA, Alert.AlertType.ERROR);
        }
    }

    private void intercambiarCuentas(int idCuenta, CuentaDto cuentaNueva) {
        for (int i = 0; i < listaCuentas.size(); i++) {
            CuentaDto cuenta = listaCuentas.get(i);
            if (cuenta.idCuenta() == idCuenta) {
                listaCuentas.set(i, cuentaNueva);
            }
        }
    }

    private void eliminarCuenta() {
        if (cuentaSeleccionada != null) {
            if (mostrarMensajeConfirmacion(BODY_CONFIRMACION_ELIMINAR_CUENTA) &&
                    gestionCuentasController.eliminarCuenta(cuentaSeleccionada.idCuenta(), cuentaSeleccionada.numCuenta())) {
                listaCuentas.remove(cuentaSeleccionada);
                limpiarSeleccion();
                mostrarMensaje(TITULO_CUENTA_ELIMINADA, BODY_CUENTA_ELIMINADA, Alert.AlertType.INFORMATION);
            }
        }
        else {
            mostrarMensaje(TITULO_CUENTA_NO_SELECCIONADA, BODY_CUENTA_NO_SELECCIONADA, Alert.AlertType.WARNING);
        }
    }

    private boolean verificarCamposLlenos() {
        return !tf_numeroCuenta.getText().isEmpty() &&
                !tf_idCuenta.getText().isEmpty() &&
                !tf_nombreBanco.getText().isEmpty() &&
                !cb_tipoCuenta.getSelectionModel().isEmpty() &&
                !cb_usuarioAsociado.getSelectionModel().isEmpty();
    }

    private boolean verificarCamposCorrectos(){
        return isInteger(tf_idCuenta.getText()) && isLong(tf_numeroCuenta.getText());
    }

    private void mostrarInformacionCuenta(CuentaDto cuenta) {
        if (cuenta != null) {
            tf_idCuenta.setText(String.valueOf(cuenta.idCuenta()));
            tf_nombreBanco.setText(cuenta.nombreBanco());
            tf_numeroCuenta.setText(cuenta.numCuenta());
            cb_usuarioAsociado.getSelectionModel().select(cuenta.idUsuarioAsociado());
            cb_tipoCuenta.getSelectionModel().select(cuenta.tipoCuenta());
        }
    }

    private void obtenerCuentas() {
        listaCuentas.addAll(gestionCuentasController.obtenerCuentas());
    }

    private void initView() {
        initDataBinding();
        obtenerCuentas();
        tb_cuentas.getItems().clear();
        tb_cuentas.setItems(listaCuentas);
        listenerSelection();
    }

    private void initDataBinding() {
        cl_idCuenta.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().idCuenta()).asObject());
        cl_nombreBanco.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreBanco()));
        cl_numeroCuenta.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().numCuenta()));
        cl_usuarioAsociado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().idUsuarioAsociado()));
        cl_tipoCuenta.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().tipoCuenta().name()));
        cl_saldo.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().saldo()).asObject());
    }

    private void listenerSelection(){
        tb_cuentas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            cuentaSeleccionada = newSelection;
            mostrarInformacionCuenta(cuentaSeleccionada);
        });
    }

    private void limpiarSeleccion() {
        tb_cuentas.getSelectionModel().clearSelection();
        limpiarCamposTexto();
    }

    private void limpiarCamposTexto() {
        tf_numeroCuenta.clear();
        tf_idCuenta.clear();
        tf_nombreBanco.clear();
        cb_tipoCuenta.getSelectionModel().clearSelection();
        cb_usuarioAsociado.getSelectionModel().clearSelection();
    }

    @FXML
    void initialize() {
        gestionCuentasController = new GestionCuentasController();
        cb_tipoCuenta.getItems().addAll(TipoCuenta.values());
        cb_usuarioAsociado.getItems().addAll(gestionCuentasController.obtenerUsuariosId());
        initView();
        assert cb_usuarioAsociado != null : "fx:id=\"cb_usuarioAsociado\" was not injected: check your FXML file 'GestionCuentas.fxml'.";
        assert bt_eliminar != null : "fx:id=\"bt_eliminar\" was not injected: check your FXML file 'GestionCuentas.fxml'.";
        assert lb_nombreBanco != null : "fx:id=\"lb_nombreBanco\" was not injected: check your FXML file 'GestionCuentas.fxml'.";
        assert cl_numeroCuenta != null : "fx:id=\"cl_numeroCuenta\" was not injected: check your FXML file 'GestionCuentas.fxml'.";
        assert tf_nombreBanco != null : "fx:id=\"tf_nombreBanco\" was not injected: check your FXML file 'GestionCuentas.fxml'.";
        assert bt_actualizar != null : "fx:id=\"bt_actualizar\" was not injected: check your FXML file 'GestionCuentas.fxml'.";
        assert tb_cuentas != null : "fx:id=\"tb_cuentas\" was not injected: check your FXML file 'GestionCuentas.fxml'.";
        assert cl_nombreBanco != null : "fx:id=\"cl_nombreBanco\" was not injected: check your FXML file 'GestionCuentas.fxml'.";
        assert cl_saldo != null : "fx:id=\"cl_saldo\" was not injected: check your FXML file 'GestionCuentas.fxml'.";
        assert lb_numeroCuenta != null : "fx:id=\"lb_numeroCuenta\" was not injected: check your FXML file 'GestionCuentas.fxml'.";
        assert cl_idCuenta != null : "fx:id=\"cl_idCuenta\" was not injected: check your FXML file 'GestionCuentas.fxml'.";
        assert tf_numeroCuenta != null : "fx:id=\"tf_numeroCuenta\" was not injected: check your FXML file 'GestionCuentas.fxml'.";
        assert cb_tipoCuenta != null : "fx:id=\"cb_tipoCuenta\" was not injected: check your FXML file 'GestionCuentas.fxml'.";
        assert bt_nuevo != null : "fx:id=\"bt_nuevo\" was not injected: check your FXML file 'GestionCuentas.fxml'.";
        assert lb_tipoCuenta != null : "fx:id=\"lb_tipoCuenta\" was not injected: check your FXML file 'GestionCuentas.fxml'.";
        assert lb_titulo != null : "fx:id=\"lb_titulo\" was not injected: check your FXML file 'GestionCuentas.fxml'.";
        assert lb_idCuenta != null : "fx:id=\"lb_idCuenta\" was not injected: check your FXML file 'GestionCuentas.fxml'.";
        assert cl_tipoCuenta != null : "fx:id=\"cl_tipoCuenta\" was not injected: check your FXML file 'GestionCuentas.fxml'.";
        assert lb_usuarioAsociado != null : "fx:id=\"lb_usuarioAsociado\" was not injected: check your FXML file 'GestionCuentas.fxml'.";
        assert tf_idCuenta != null : "fx:id=\"tf_idCuenta\" was not injected: check your FXML file 'GestionCuentas.fxml'.";
        assert cl_usuarioAsociado != null : "fx:id=\"cl_usuarioAsociado\" was not injected: check your FXML file 'GestionCuentas.fxml'.";
        assert bt_limpiar != null : "fx:id=\"bt_limpiar\" was not injected: check your FXML file 'GestionCuentas.fxml'.";

    }
}
