package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.viewController;

import java.net.URL;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller.GestionTransaccionesUsuarioController;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.TransaccionDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.UsuarioDto;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.BilleteraVirtualConstantes.*;
import static co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.MetodosReutilizables.*;

public class GestionTransaccionesUsuarioViewController {

    private UsuarioDto usuario;
    private GestionTransaccionesUsuarioController gestionTransaccionesUsuarioController;
    ObservableList<TransaccionDto> listaTransacciones = FXCollections.observableArrayList();
    TransaccionDto transaccionSeleccionada;
    private FilteredList<String> filtroCuentasDestino;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cb_categoria;

    @FXML
    private Button bt_realizarTransaccion;

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
    private TableColumn<TransaccionDto, String> cl_descripcion;

    @FXML
    private ComboBox<String> cb_cuentaOrigen;

    @FXML
    private TableColumn<TransaccionDto, Double> cl_monto;

    @FXML
    private TableView<TransaccionDto> tb_transacciones;

    @FXML
    private TableColumn<TransaccionDto, String> cl_fecha;

    @FXML
    private TableColumn<TransaccionDto, String> cl_categoria;

    @FXML
    private ComboBox<String> cb_cuentaDestino;

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
        gestionTransaccionesUsuarioController = new GestionTransaccionesUsuarioController();
        manejarSeleccionCategoria();
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
        initView();
        inicializarComboBox();
    }

    private void crearListaFiltrada(LinkedList<String> listaCuentasDestino) {
        ObservableList<String> listaCuentasDestinoFiltrada = FXCollections.observableArrayList(listaCuentasDestino);
        filtroCuentasDestino = new FilteredList<>(listaCuentasDestinoFiltrada, cuentas -> true);
        cb_cuentaDestino.setItems(filtroCuentasDestino);
    }

    private void initView() {
        initDataBinding();
        obtenerTransacciones();
        tb_transacciones.getItems().clear();
        tb_transacciones.setItems(listaTransacciones);
        listenerSelection();
    }

    private void obtenerTransacciones() {
        listaTransacciones.addAll(gestionTransaccionesUsuarioController.obtenerTransacciones(usuario.idUsuario()));
    }

    private void listenerSelection(){
        tb_transacciones.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            transaccionSeleccionada = newSelection;
            mostrarInformacionTransaccion(transaccionSeleccionada);
        });
    }

    private void mostrarInformacionTransaccion(TransaccionDto transaccionSeleccionada) {
        if (transaccionSeleccionada != null) {
            cb_categoria.getSelectionModel().select(transaccionSeleccionada.nombreCategoria());
            dp_fecha.setValue(transaccionSeleccionada.fecha());
            tf_monto.setText(Double.toString(transaccionSeleccionada.monto()));
            tf_descripcion.setText(transaccionSeleccionada.descripcion());
            cb_cuentaOrigen.getSelectionModel().select(transaccionSeleccionada.numCuentaOrigen());
            cb_cuentaDestino.getSelectionModel().select(transaccionSeleccionada.numCuentaDestino());
        }
    }

    private TransaccionDto crearTransaccion(){
        return new TransaccionDto(
                gestionTransaccionesUsuarioController.obtenerNuevoIdTransaccion(),
                dp_fecha.getValue(),
                Double.parseDouble(tf_monto.getText()),
                tf_descripcion.getText(),
                usuario.idUsuario(),
                cb_cuentaOrigen.getSelectionModel().getSelectedItem(),
                cb_cuentaDestino.getSelectionModel().getSelectedItem(),
                cb_categoria.getSelectionModel().getSelectedItem());
    }

    private void inicializarComboBox() {
        cb_categoria.getItems().addAll();
        cb_categoria.getItems().addAll(gestionTransaccionesUsuarioController.obtenerCategoriasNombresDeUsuario(usuario.idUsuario()));
        cb_categoria.setOnAction(event -> manejarSeleccionCategoria());
        cb_cuentaOrigen.setOnAction(event -> manejarSeleccionCuentaOrigen());
        LinkedList<String> listaNumCuentas = gestionTransaccionesUsuarioController.obtenerNumCuentasUsuario(usuario.idUsuario());
        cb_cuentaOrigen.getItems().addAll(listaNumCuentas);
        crearListaFiltrada(listaNumCuentas);
    }

    private void realizarMovimiento() {
        if (verificarCamposLlenos()) {
            if (verificarCamposCorrectos()) {
                String categoria = cb_categoria.getSelectionModel().getSelectedItem();
                TransaccionDto transaccionDto = crearTransaccion();
                if (categoria.equalsIgnoreCase("Deposito")) {
                    realizarDeposito(transaccionDto);
                }
                else if (categoria.equalsIgnoreCase("Transferencia")) {
                    realizarTransferencia(transaccionDto);
                }
                else if (categoria.equalsIgnoreCase("Retiro")) {
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
        if (gestionTransaccionesUsuarioController.agregarTransaccion(transaccionDto, usuario.idUsuario())){
            mostrarMensajeTransaccionExitosa(transaccionDto);
            listaTransacciones.add(transaccionDto);
        }
        else {
            mostrarMensaje(TITULO_DEPOSITO_NO_EXITOSO, BODY_DEPOSITO_NO_EXITOSO, Alert.AlertType.ERROR);
        }
    }

    private boolean falloSacarDinero(TransaccionDto transaccionDto) {
        if (gestionTransaccionesUsuarioController.saldoCuentaEsSuficiente(transaccionDto)) {
            if (gestionTransaccionesUsuarioController.validarPresupuesto(transaccionDto)) {
                if (gestionTransaccionesUsuarioController.agregarTransaccion(transaccionDto, usuario.idUsuario())){
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
        if (transaccionDto.nombreCategoria().equalsIgnoreCase("Deposito")) {
            mostrarMensaje(TITULO_DEPOSITO_EXITOSO,
                    BODY_DEPOSITO_EXITOSO + mensajeMonto,
                    Alert.AlertType.INFORMATION);
        }
        else if (transaccionDto.nombreCategoria().equalsIgnoreCase("Transferencia")) {
            mostrarMensaje(TITULO_TRANSFERENCIA_EXITOSA,
                    BODY_TRANSFERENCIA_EXITOSA + mensajeMonto,
                    Alert.AlertType.INFORMATION);

        }
        else if (transaccionDto.nombreCategoria().equalsIgnoreCase("Retiro")) {
            mostrarMensaje(TITULO_RETIRO_EXITOSO,
                    BODY_RETIRO_EXITOSO + mensajeMonto,
                    Alert.AlertType.INFORMATION);
        }
        limpiarSeleccion();
    }

    private void limpiarSeleccion() {
        cb_categoria.getSelectionModel().clearSelection();
        cb_cuentaOrigen.getSelectionModel().clearSelection();
        cb_categoria.getSelectionModel().clearSelection();
        dp_fecha.setValue(null);
        cb_cuentaDestino.getSelectionModel().clearSelection();
        tf_monto.setText(null);
        tf_descripcion.setText(null);
        tb_transacciones.getSelectionModel().clearSelection();
        filtroCuentasDestino.setPredicate(cuenta -> true);
    }

    private void manejarSeleccionCuentaOrigen() {
        String cuentaOrigen = cb_cuentaOrigen.getSelectionModel().getSelectedItem();
        if (cuentaOrigen != null && cb_categoria.getSelectionModel().getSelectedItem().equalsIgnoreCase("Transferencia")) {
            filtroCuentasDestino.setPredicate(cuenta -> !cuenta.equalsIgnoreCase(cuentaOrigen));
            cb_cuentaDestino.setDisable(false);
        }
    }

    private void manejarSeleccionCategoria() {
        String categoria = cb_categoria.getSelectionModel().getSelectedItem();
        cb_cuentaOrigen.setDisable(false);
        cb_cuentaDestino.setDisable(false);
        tf_monto.setDisable(false);
        dp_fecha.setDisable(false);
        tf_descripcion.setDisable(false);
        if (categoria == null || categoria.isEmpty()) {
            cb_cuentaOrigen.setDisable(true);
            cb_cuentaDestino.setDisable(true);
            tf_monto.setDisable(true);
            dp_fecha.setDisable(true);
            tf_descripcion.setDisable(true);
        } else {
            limpiarCampos();
            cb_cuentaDestino.setDisable(true);
        }
    }

    private void limpiarCampos() {
        cb_cuentaOrigen.getSelectionModel().clearSelection();
        dp_fecha.setValue(null);
        cb_cuentaDestino.getSelectionModel().clearSelection();
        tf_monto.clear();
        tf_descripcion.clear();
    }

    private boolean verificarCamposLlenos() {
        if (!cb_categoria.getSelectionModel().isEmpty()
                && !cb_cuentaOrigen.getSelectionModel().isEmpty()
                && dp_fecha.getValue() != null
                && !tf_monto.getText().isEmpty()) {
            if (cb_categoria.getSelectionModel().getSelectedItem().equalsIgnoreCase("Transferencia")) {
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
        cl_categoria.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreCategoria()));
    }
}