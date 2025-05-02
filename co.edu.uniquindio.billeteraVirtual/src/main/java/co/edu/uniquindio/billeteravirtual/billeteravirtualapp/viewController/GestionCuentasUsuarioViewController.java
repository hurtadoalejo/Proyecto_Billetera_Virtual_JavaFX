package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.viewController;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller.GestionCuentasUsuarioController;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.CuentaDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.UsuarioDto;
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

public class GestionCuentasUsuarioViewController {

    private UsuarioDto usuario;
    private GestionCuentasUsuarioController gestionCuentasUsuarioController;
    ObservableList<CuentaDto> listaCuentas = FXCollections.observableArrayList();
    CuentaDto cuentaSeleccionada;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bt_eliminar;

    @FXML
    private TableColumn<CuentaDto, String> cl_numeroCuenta;

    @FXML
    private ComboBox<String> cb_presupuestoAsociado;

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
    private TableColumn<CuentaDto, Integer> cl_idCuenta;

    @FXML
    private TextField tf_numeroCuenta;

    @FXML
    private ComboBox<TipoCuenta> cb_tipoCuenta;

    @FXML
    private Button bt_nuevo;

    @FXML
    private TableColumn<CuentaDto, String> cl_tipoCuenta;

    @FXML
    private Label lb_numeroCuent;

    @FXML
    private TextField tf_idCuenta;

    @FXML
    private Button bt_limpiar;

    @FXML
    private Label lb_nombreBanc;

    @FXML
    private TableColumn<CuentaDto, String> cl_presupuestoAsociado;

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

    @FXML
    void initialize() {
        gestionCuentasUsuarioController = new GestionCuentasUsuarioController();
        cb_tipoCuenta.getItems().addAll(TipoCuenta.values());
    }

    public void setUsuario(UsuarioDto usuarioDto) {
        usuario = usuarioDto;
        initView();
        configurarComboBoxPresupuestos();
    }

    private void configurarComboBoxPresupuestos() {
        cb_presupuestoAsociado.getItems().clear();
        cb_presupuestoAsociado.getItems().addAll(gestionCuentasUsuarioController.obtenerPresupuestosDisponiblesNombres(usuario.idUsuario()));
    }

    private CuentaDto crearCuenta() {
        return new CuentaDto(Integer.parseInt(tf_idCuenta.getText()), tf_nombreBanco.getText(),
                tf_numeroCuenta.getText(), usuario.idUsuario(), cb_tipoCuenta.getValue(),
                0, cb_presupuestoAsociado.getValue());
    }

    private CuentaDto crearCuentaConSaldo(Double saldo) {
        return new CuentaDto(Integer.parseInt(tf_idCuenta.getText()), tf_nombreBanco.getText(),
                tf_numeroCuenta.getText(), usuario.idUsuario(), cb_tipoCuenta.getValue(),
                saldo, cb_presupuestoAsociado.getValue());
    }

    private void agregarCuenta() {
        if (verificarCamposLlenos()) {
            if (verificarCamposCorrectos()) {
                CuentaDto cuenta = crearCuenta();
                if (gestionCuentasUsuarioController.agregarCuentaUsuario(usuario.idUsuario(), cuenta)) {
                    listaCuentas.add(cuenta);
                    tb_cuentas.refresh();
                    configurarComboBoxPresupuestos();
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
                    if (gestionCuentasUsuarioController.
                            actualizarCuentaUsuario(usuario.idUsuario(), cuentaSeleccionada, cuentaNueva)) {
                        intercambiarCuentas(cuentaSeleccionada.idCuenta(), cuentaNueva);
                        configurarComboBoxPresupuestos();
                        limpiarSeleccion();
                        tb_cuentas.refresh();
                        mostrarMensaje(TITULO_CUENTA_ACTUALIZADA,
                                BODY_CUENTA_ACTUALIZADA, Alert.AlertType.INFORMATION);
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
            mostrarMensaje(TITULO_CUENTA_NO_SELECCIONADA,
                    BODY_CUENTA_NO_SELECCIONADA, Alert.AlertType.WARNING);
        }
    }

    private void mostrarMensajeNoActualizarCuenta(CuentaDto cuenta) {
        int idCuenta = Integer.parseInt(tf_idCuenta.getText());
        String numCuenta = tf_numeroCuenta.getText();
        if (gestionCuentasUsuarioController.verificarCuentaId(idCuenta)
                && cuenta.idCuenta() != idCuenta) {
            mostrarMensaje(TITULO_CUENTA_NO_ACTUALIZADA, BODY_CUENTA_NO_ACTUALIZADA_ID, Alert.AlertType.ERROR);
        }
        if (gestionCuentasUsuarioController.verificarCuentaNumCuenta(numCuenta)
                && !cuenta.numCuenta().equals(numCuenta)) {
            mostrarMensaje(TITULO_CUENTA_NO_ACTUALIZADA,
                    BODY_CUENTA_NO_ACTUALIZADA_NUM_CUENTA, Alert.AlertType.ERROR);
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
                    gestionCuentasUsuarioController.eliminarCuentaUsuario(usuario.idUsuario(),
                            cuentaSeleccionada.idCuenta(), cuentaSeleccionada.numCuenta())) {
                listaCuentas.remove(cuentaSeleccionada);
                configurarComboBoxPresupuestos();
                limpiarSeleccion();
                mostrarMensaje(TITULO_CUENTA_ELIMINADA, BODY_CUENTA_ELIMINADA, Alert.AlertType.INFORMATION);
            }
        }
        else {
            mostrarMensaje(TITULO_CUENTA_NO_SELECCIONADA, BODY_CUENTA_NO_SELECCIONADA, Alert.AlertType.WARNING);
        }
    }

    private boolean verificarCamposLlenos() {
        return !tf_numeroCuenta.getText().isEmpty() && !tf_idCuenta.getText().isEmpty()
                && !tf_nombreBanco.getText().isEmpty() && !cb_tipoCuenta.getSelectionModel().isEmpty()
                && !cb_presupuestoAsociado.getSelectionModel().getSelectedItem().isEmpty();
    }

    private boolean verificarCamposCorrectos(){
        return isInteger(tf_idCuenta.getText()) && isLong(tf_numeroCuenta.getText());
    }

    private void mostrarInformacionCuenta(CuentaDto cuenta) {
        if (cuenta != null) {
            tf_idCuenta.setText(String.valueOf(cuenta.idCuenta()));
            tf_nombreBanco.setText(cuenta.nombreBanco());
            tf_numeroCuenta.setText(cuenta.numCuenta());
            cb_tipoCuenta.getSelectionModel().select(cuenta.tipoCuenta());
            cb_presupuestoAsociado.getSelectionModel().select(cuenta.presupuestoAsociado());
        }
    }

    private void obtenerCuentas() {
        listaCuentas.addAll(gestionCuentasUsuarioController.obtenerCuentas(usuario.idUsuario()));
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
        cl_presupuestoAsociado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().presupuestoAsociado()));
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
        cb_presupuestoAsociado.getSelectionModel().select(null);
    }
}