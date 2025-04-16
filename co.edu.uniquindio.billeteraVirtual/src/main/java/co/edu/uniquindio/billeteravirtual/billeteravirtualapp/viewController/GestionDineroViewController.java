package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.viewController;


import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller.GestionDineroController;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.TransaccionDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.UsuarioDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.TipoTransaccion;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import static co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.BilleteraVirtualConstantes.*;
import static co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.MetodosReutilizables.*;

public class GestionDineroViewController {

    private GestionDineroController gestionDineroController;
    private UsuarioDto usuario;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bt_limpiar1;

    @FXML
    private ComboBox<String> cb_categoria;

    @FXML
    private Label lb_cuentaOrigen;

    @FXML
    private TextField tf_cuentaDestino;

    @FXML
    private Label lb_cuentaDestino;

    @FXML
    private Label lb_monto;

    @FXML
    private Label lb_tipoMovimiento;

    @FXML
    private TextField tf_descripcion;

    @FXML
    private TextField tf_monto;

    @FXML
    private ComboBox<TipoTransaccion> cb_tipoMovimiento;

    @FXML
    private Label lb_categoria;

    @FXML
    private Button bt_realizarMovimiento;

    @FXML
    private DatePicker dp_fecha;

    @FXML
    private ComboBox<String> cb_cuentaOrigen;

    @FXML
    private Label lb_descripcion;

    @FXML
    private Label lb_titulo;

    @FXML
    private Label lb_fecha;

    @FXML
    void onLimpiar() {
        limpiarSeleccion();
    }

    @FXML
    void onRealizarMovimiento() {
        realizarMovimiento();
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
        inicializarComboBox();
    }

    private TransaccionDto crearTransaccion(){
        return new TransaccionDto(gestionDineroController.obtenerNuevoIdTransaccion(), dp_fecha.getValue(),
                Double.parseDouble(tf_monto.getText()), tf_descripcion.getText(),
                cb_tipoMovimiento.getSelectionModel().getSelectedItem(), usuario.idUsuario(),
                cb_cuentaOrigen.getSelectionModel().getSelectedItem(), tf_cuentaDestino.getText(),
                cb_categoria.getSelectionModel().getSelectedItem());
    }

    private void inicializarComboBox() {
        cb_tipoMovimiento.getItems().addAll(TipoTransaccion.values());
        cb_tipoMovimiento.setOnAction(event -> manejarSeleccionTipoTransaccion());
        cb_cuentaOrigen.getItems().addAll(gestionDineroController.
                obtenerNumCuentasUsuario(usuario.idUsuario()));
        cb_categoria.getItems().addAll(gestionDineroController.
                obtenerCategoriasNombresDeUsuario(usuario.idUsuario()));
    }

    private void realizarMovimiento() {
        if (verificarCamposLlenos()) {
            if (verificarCamposCorrectos()) {
                TipoTransaccion tipoTransaccion = cb_tipoMovimiento.getSelectionModel().getSelectedItem();
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
        if (gestionDineroController.agregarTransaccion(transaccionDto, usuario.idUsuario())){
            mostrarMensajeTransaccionExitosa(transaccionDto);
        }
        else {
            mostrarMensaje(TITULO_DEPOSITO_NO_EXITOSO, BODY_DEPOSITO_NO_EXITOSO, Alert.AlertType.ERROR);
        }
    }

    private void realizarRetiro(TransaccionDto transaccionDto) {
        if (gestionDineroController.saldoCuentaEsSuficiente(transaccionDto, usuario.idUsuario())) {
            if (transaccionPasaPresupuesto(transaccionDto)) {
                if (gestionDineroController.agregarTransaccion(transaccionDto, usuario.idUsuario())){
                    mostrarMensajeTransaccionExitosa(transaccionDto);
                }
            }
            else {
                mostrarMensaje(TITULO_PRESUPUESTO_SUPERADO, BODY_PRESUPUESTO_SUPERADO, Alert.AlertType.ERROR);
            }
        }
        else {
            mostrarMensaje(TITULO_RETIRO_NO_EXITOSO, BODY_RETIRO_NO_EXITOSO, Alert.AlertType.ERROR);
        }
    }

    private void realizarTransferencia(TransaccionDto transaccionDto) {
        if (gestionDineroController.cuentasExisten(transaccionDto.numCuentaOrigen(),
                transaccionDto.numCuentaDestino())) {
            if (gestionDineroController.saldoCuentaEsSuficiente(transaccionDto, usuario.idUsuario())) {
                if (transaccionPasaPresupuesto(transaccionDto)) {
                    if (gestionDineroController.agregarTransaccion(transaccionDto, usuario.idUsuario())){
                        mostrarMensajeTransaccionExitosa(transaccionDto);
                    }
                }
                else {
                    mostrarMensaje(TITULO_PRESUPUESTO_SUPERADO, BODY_PRESUPUESTO_SUPERADO, Alert.AlertType.ERROR);
                }
            }
            else {
                mostrarMensaje(TITULO_TRANSFERENCIA_NO_EXITOSA,
                        BODY_TRANSFERENCIA_NO_EXITOSA_DINERO, Alert.AlertType.ERROR);
            }
        }
        else {
            mostrarMensaje(TITULO_TRANSFERENCIA_NO_EXITOSA,
                    BODY_TRANSFERENCIA_NO_EXITOSA_CUENTA, Alert.AlertType.ERROR);
        }
    }

    private boolean transaccionPasaPresupuesto(TransaccionDto transaccion) {
        if (!cb_categoria.getSelectionModel().isEmpty()) {
            return gestionDineroController.transaccionPasaPresupuesto(usuario.idUsuario(), transaccion,
                    cb_categoria.getValue());
        }
        return true;
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
        cb_categoria.getSelectionModel().clearSelection();
        cb_cuentaOrigen.getSelectionModel().clearSelection();
        cb_tipoMovimiento.getSelectionModel().clearSelection();
        dp_fecha.setValue(null);
        tf_cuentaDestino.setText(null);
        tf_monto.setText(null);
        tf_descripcion.setText(null);
    }

    private void manejarSeleccionTipoTransaccion() {
        TipoTransaccion tipoTransaccion = cb_tipoMovimiento.getSelectionModel().getSelectedItem();
        cb_cuentaOrigen.setDisable(false);
        tf_cuentaDestino.setDisable(false);
        tf_monto.setDisable(false);
        dp_fecha.setDisable(false);
        tf_descripcion.setDisable(false);
        cb_categoria.setDisable(false);
        if (tipoTransaccion == null) {
            cb_cuentaOrigen.setDisable(true);
            tf_cuentaDestino.setDisable(true);
            tf_monto.setDisable(true);
            dp_fecha.setDisable(true);
            tf_descripcion.setDisable(true);
            cb_categoria.setDisable(true);
        }
        else if (tipoTransaccion.equals(TipoTransaccion.TRANSFERENCIA)) {
            limpiarCampos();
            tf_cuentaDestino.setDisable(false);
        }
        else {
            limpiarCampos();
            tf_cuentaDestino.setDisable(true);
        }
    }

    private void limpiarCampos() {
        cb_categoria.getSelectionModel().clearSelection();
        cb_cuentaOrigen.getSelectionModel().clearSelection();
        dp_fecha.setValue(null);
        tf_cuentaDestino.clear();
        tf_monto.clear();
        tf_descripcion.clear();
    }

    private boolean verificarCamposLlenos() {
        if (!cb_tipoMovimiento.getSelectionModel().isEmpty() &&
                !cb_cuentaOrigen.getSelectionModel().isEmpty() &&
                dp_fecha.getValue() != null &&
                !tf_monto.getText().isEmpty()) {
            if (cb_tipoMovimiento.getSelectionModel().getSelectedItem().equals(TipoTransaccion.TRANSFERENCIA)) {
                return !tf_cuentaDestino.getText().isEmpty();
            }
            return true;
        }
        return false;
    }

    private boolean verificarCamposCorrectos(){
        if (isDouble(tf_monto.getText())) {
            if (cb_tipoMovimiento.getSelectionModel().getSelectedItem().equals(TipoTransaccion.TRANSFERENCIA)) {
                return isLong(tf_cuentaDestino.getText());
            }
            return true;
        }
        return false;
    }

    @FXML
    void initialize() {
        gestionDineroController = new GestionDineroController();
        manejarSeleccionTipoTransaccion();
        dp_fecha.setEditable(false);
        assert bt_limpiar1 != null : "fx:id=\"bt_limpiar1\" was not injected: check your FXML file 'GestionDinero.fxml'.";
        assert cb_categoria != null : "fx:id=\"cb_categoria\" was not injected: check your FXML file 'GestionDinero.fxml'.";
        assert lb_cuentaOrigen != null : "fx:id=\"lb_cuentaOrigen\" was not injected: check your FXML file 'GestionDinero.fxml'.";
        assert tf_cuentaDestino != null : "fx:id=\"tf_cuentaDestino\" was not injected: check your FXML file 'GestionDinero.fxml'.";
        assert lb_cuentaDestino != null : "fx:id=\"lb_cuentaDestino\" was not injected: check your FXML file 'GestionDinero.fxml'.";
        assert lb_monto != null : "fx:id=\"lb_monto\" was not injected: check your FXML file 'GestionDinero.fxml'.";
        assert lb_tipoMovimiento != null : "fx:id=\"lb_tipoMovimiento\" was not injected: check your FXML file 'GestionDinero.fxml'.";
        assert tf_descripcion != null : "fx:id=\"tf_descripcion\" was not injected: check your FXML file 'GestionDinero.fxml'.";
        assert tf_monto != null : "fx:id=\"tf_monto\" was not injected: check your FXML file 'GestionDinero.fxml'.";
        assert cb_tipoMovimiento != null : "fx:id=\"cb_tipoMovimiento\" was not injected: check your FXML file 'GestionDinero.fxml'.";
        assert lb_categoria != null : "fx:id=\"lb_lb_categoria\" was not injected: check your FXML file 'GestionDinero.fxml'.";
        assert bt_realizarMovimiento != null : "fx:id=\"bt_realizarMovimiento\" was not injected: check your FXML file 'GestionDinero.fxml'.";
        assert dp_fecha != null : "fx:id=\"dp_fecha\" was not injected: check your FXML file 'GestionDinero.fxml'.";
        assert cb_cuentaOrigen != null : "fx:id=\"cb_cuentaOrigen\" was not injected: check your FXML file 'GestionDinero.fxml'.";
        assert lb_descripcion != null : "fx:id=\"lb_descripcion\" was not injected: check your FXML file 'GestionDinero.fxml'.";
        assert lb_titulo != null : "fx:id=\"lb_titulo\" was not injected: check your FXML file 'GestionDinero.fxml'.";
        assert lb_fecha != null : "fx:id=\"lb_fecha\" was not injected: check your FXML file 'GestionDinero.fxml'.";

    }
}