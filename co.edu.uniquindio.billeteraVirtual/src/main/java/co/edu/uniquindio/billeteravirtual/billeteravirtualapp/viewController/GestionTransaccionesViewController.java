package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.viewController;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller.GestionTransaccionesController;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.TransaccionDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.TipoTransaccion;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.BilleteraVirtualConstantes.*;
import static co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.BilleteraVirtualConstantes.BODY_INCOMPLETO;
import static co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.MetodosReutilizables.isDouble;
import static co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.MetodosReutilizables.mostrarMensaje;

public class GestionTransaccionesViewController {

    private GestionTransaccionesController gestionTransaccionesController;
    ObservableList<TransaccionDto> listaTransacciones = FXCollections.observableArrayList();
    TransaccionDto transaccionSeleccionada;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<TipoTransaccion> cb_tipoTransaccion;

    @FXML
    private TableColumn<TransaccionDto, String> cl_cuentaOrigen;

    @FXML
    private TableColumn<TransaccionDto, String> cl_cuentaDestino;

    @FXML
    private TextField tf_monto;

    @FXML
    private TextField tf_descripcion;

    @FXML
    private DatePicker dp_fecha;

    @FXML
    private TableColumn<TransaccionDto, Integer> cl_idTransaccion;

    @FXML
    private TableColumn<TransaccionDto, String> cl_usuario;

    @FXML
    private TableColumn<TransaccionDto, String> cl_descripcion;

    @FXML
    private ComboBox<String> cb_usuario;

    @FXML
    private ComboBox<String> cb_cuentaOrigen;

    @FXML
    private TableColumn<TransaccionDto, Double> cl_monto;

    @FXML
    private TableView<TransaccionDto> tb_transacciones;

    @FXML
    private TableColumn<TransaccionDto, String> cl_fecha;

    @FXML
    private TableColumn<TransaccionDto, String> cl_tipoTransaccion;

    @FXML
    private ComboBox<String> cb_cuentaDestino;

    @FXML
    private Button bt_realizarTransaccion;

    @FXML
    private Button bt_limpiar;

    @FXML
    void onLimpiar() {
        limpiarSeleccion();
    }

    @FXML
    void onRealizarTransaccion() {
        realizarMovimiento();
    }

    @FXML
    void initialize() {
        gestionTransaccionesController = new GestionTransaccionesController();
        initView();
    }

    private void initView() {
        initDataBinding();
        obtenerTransacciones();
        tb_transacciones.getItems().clear();
        tb_transacciones.setItems(listaTransacciones);
        cb_usuario.getItems().clear();
        cb_usuario.getItems().setAll(gestionTransaccionesController.obtenerUsuariosId());
        cb_tipoTransaccion.getItems().addAll(TipoTransaccion.values());
        listenerSelection();
        listenerUsuarioSeleccionado();
        listenerTipoTransaccionSeleccionado();
        listenerCuentaOrigenSeleccionada();
        limpiarSeleccion();
    }

    private void obtenerTransacciones() {
        listaTransacciones.addAll(gestionTransaccionesController.obtenerTransacciones());
    }

    private void listenerSelection(){
        tb_transacciones.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            transaccionSeleccionada = newSelection;
            mostrarInformacionTransaccion(transaccionSeleccionada);
        });
    }

    private void mostrarInformacionTransaccion(TransaccionDto transaccionSeleccionada) {
        if (transaccionSeleccionada != null) {
            cb_tipoTransaccion.getSelectionModel().select(transaccionSeleccionada.tipoTransaccion());
            dp_fecha.setValue(transaccionSeleccionada.fecha());
            tf_monto.setText(Double.toString(transaccionSeleccionada.monto()));
            tf_descripcion.setText(transaccionSeleccionada.descripcion());
            cb_cuentaOrigen.getSelectionModel().select(transaccionSeleccionada.numCuentaOrigen());
            cb_cuentaDestino.getSelectionModel().select(transaccionSeleccionada.numCuentaDestino());
            cb_usuario.getSelectionModel().select(transaccionSeleccionada.idUsuario());
        }
    }

    private TransaccionDto crearTransaccion(){
        return new TransaccionDto(
                gestionTransaccionesController.obtenerNuevoIdTransaccion(),
                dp_fecha.getValue(),
                Double.parseDouble(tf_monto.getText()),
                tf_descripcion.getText(),
                cb_usuario.getValue(),
                cb_cuentaOrigen.getSelectionModel().getSelectedItem(),
                cb_cuentaDestino.getSelectionModel().getSelectedItem(),
                cb_tipoTransaccion.getSelectionModel().getSelectedItem());
    }

    private void realizarMovimiento() {
        if (verificarCamposLlenos()) {
            if (verificarCamposCorrectos()) {
                TipoTransaccion tipoTransaccion = cb_tipoTransaccion.getSelectionModel().getSelectedItem();
                TransaccionDto transaccionDto = crearTransaccion();
                if (tipoTransaccion.equals(TipoTransaccion.DEPOSITO)) {
                    realizarDeposito(transaccionDto);
                }
                else if (tipoTransaccion.equals(TipoTransaccion.TRANSFERENCIA)) {
                    realizarTransferencia(transaccionDto);
                }
                else if (tipoTransaccion.equals(TipoTransaccion.RETIRO)) {
                    realizarRetiro(transaccionDto);
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

    private void realizarDeposito(TransaccionDto transaccionDto) {
        if (gestionTransaccionesController.agregarTransaccion(transaccionDto, cb_usuario.getValue())){
            mostrarMensajeTransaccionExitosa(transaccionDto);
            listaTransacciones.add(transaccionDto);
        }
        else {
            mostrarMensaje(TITULO_DEPOSITO_NO_EXITOSO, BODY_DEPOSITO_NO_EXITOSO, Alert.AlertType.ERROR);
        }
    }

    private boolean falloSacarDinero(TransaccionDto transaccionDto) {
        if (gestionTransaccionesController.saldoCuentaEsSuficiente(transaccionDto)) {
            if (gestionTransaccionesController.validarPresupuesto(transaccionDto)) {
                if (gestionTransaccionesController.agregarTransaccion(transaccionDto, cb_usuario.getValue())){
                    mostrarMensajeTransaccionExitosa(transaccionDto);
                    listaTransacciones.add(transaccionDto);
                }
            }
            else {
                mostrarMensaje(TITULO_PRESUPUESTO_SUPERADO, BODY_PRESUPUESTO_SUPERADO, Alert.AlertType.ERROR);
            }
            return false;
        }
        return true;
    }

    private void realizarRetiro(TransaccionDto transaccionDto) {
        if (falloSacarDinero(transaccionDto)) {
            mostrarMensaje(TITULO_RETIRO_NO_EXITOSO,
                    BODY_RETIRO_NO_EXITOSO, Alert.AlertType.ERROR);
        }
    }

    private void realizarTransferencia(TransaccionDto transaccionDto) {
        if (falloSacarDinero(transaccionDto)) {
            mostrarMensaje(TITULO_TRANSFERENCIA_NO_EXITOSA,
                    BODY_TRANSFERENCIA_NO_EXITOSA_DINERO, Alert.AlertType.ERROR);
        }
    }

    private void mostrarMensajeTransaccionExitosa(TransaccionDto transaccionDto) {
        String mensajeMonto = transaccionDto.monto() + " pesos.";
        if (transaccionDto.tipoTransaccion().equals(TipoTransaccion.DEPOSITO)) {
            mostrarMensaje(TITULO_DEPOSITO_EXITOSO,
                    BODY_DEPOSITO_EXITOSO + mensajeMonto,
                    Alert.AlertType.INFORMATION);
        }
        else if (transaccionDto.tipoTransaccion().equals(TipoTransaccion.TRANSFERENCIA)) {
            mostrarMensaje(TITULO_TRANSFERENCIA_EXITOSA,
                    BODY_TRANSFERENCIA_EXITOSA + mensajeMonto,
                    Alert.AlertType.INFORMATION);

        }
        else if (transaccionDto.tipoTransaccion().equals(TipoTransaccion.RETIRO)) {
            mostrarMensaje(TITULO_RETIRO_EXITOSO,
                    BODY_RETIRO_EXITOSO + mensajeMonto,
                    Alert.AlertType.INFORMATION);
        }
        limpiarSeleccion();
    }

    private void limpiarSeleccion() {
        tb_transacciones.getSelectionModel().clearSelection();
        limpiarCampos();
        desabilitarCamposFormulario();
    }

    private void limpiarCampos() {
        cb_usuario.setValue(null);
        cb_tipoTransaccion.setValue(null);
        cb_cuentaOrigen.setValue(null);
        cb_tipoTransaccion.setValue(null);
        dp_fecha.setValue(null);
        cb_cuentaDestino.setValue(null);
        tf_monto.setText(null);
        tf_descripcion.setText(null);
        tb_transacciones.getSelectionModel().clearSelection();
    }

    private boolean verificarCamposLlenos() {
        if (!cb_tipoTransaccion.getSelectionModel().isEmpty()
                && !cb_cuentaOrigen.getSelectionModel().isEmpty()
                && dp_fecha.getValue() != null
                && !tf_monto.getText().isEmpty()
                && !cb_usuario.getSelectionModel().isEmpty()) {
            if (cb_tipoTransaccion.getValue().equals(TipoTransaccion.TRANSFERENCIA)) {
                return !cb_cuentaDestino.getSelectionModel().isEmpty();
            }
            return true;
        }
        return false;
    }

    private boolean verificarCamposCorrectos(){
        return isDouble(tf_monto.getText());
    }

    private void initDataBinding() {
        cl_idTransaccion.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().idTransaccion()).asObject());
        cl_fecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().fecha().toString()));
        cl_monto.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().monto()).asObject());
        cl_descripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().descripcion()));
        cl_cuentaDestino.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().numCuentaDestino()));
        cl_cuentaOrigen.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().numCuentaOrigen()));
        cl_tipoTransaccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().tipoTransaccion().name()));
        cl_usuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().idUsuario()));
    }

    private void desabilitarCamposFormulario() {
        cb_tipoTransaccion.setDisable(true);
        cb_cuentaOrigen.setDisable(true);
        cb_cuentaDestino.setDisable(true);
        cb_cuentaDestino.getItems().clear();
        cb_cuentaOrigen.getItems().clear();
    }

    private void listenerUsuarioSeleccionado() {
        cb_usuario.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                cb_cuentaOrigen.getItems().clear();
                cb_cuentaOrigen.getItems().addAll(gestionTransaccionesController.obtenerNumCuentasUsuario(newSelection));
                cb_tipoTransaccion.setDisable(false);
            } else {
                desabilitarCamposFormulario();
            }
        });
    }

    private void listenerTipoTransaccionSeleccionado() {
        cb_tipoTransaccion.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                if (newSelection.equals(TipoTransaccion.TRANSFERENCIA)) {
                    cb_cuentaOrigen.setDisable(false);
                    cb_cuentaOrigen.getSelectionModel().clearSelection();
                } else {
                    cb_cuentaOrigen.setDisable(false);
                    cb_cuentaOrigen.getSelectionModel().clearSelection();
                    cb_cuentaDestino.setDisable(true);
                    cb_cuentaDestino.setValue(null);
                }
            }
        });
    }

    private void listenerCuentaOrigenSeleccionada() {
        cb_cuentaOrigen.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                if (cb_tipoTransaccion.getValue().equals(TipoTransaccion.TRANSFERENCIA)) {
                    cb_cuentaDestino.setDisable(false);
                    FilteredList<String> filtroCuentasDestino = new FilteredList<>
                            (cb_cuentaOrigen.getItems(), cuenta -> !cuenta.equals(cb_cuentaOrigen.getValue()));
                    cb_cuentaDestino.getItems().addAll(filtroCuentasDestino);
                }
            } else {
                cb_cuentaDestino.setDisable(true);
                cb_cuentaDestino.getItems().clear();
            }
        });
    }
}