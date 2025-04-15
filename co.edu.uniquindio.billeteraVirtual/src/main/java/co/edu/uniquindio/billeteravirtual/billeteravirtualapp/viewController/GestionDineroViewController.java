package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.viewController;


import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller.GestionDineroController;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.TransaccionDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.UsuarioDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.model.TipoTransaccion;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import jdk.swing.interop.SwingInterOpUtils;

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

    private TransaccionDto crearTransaccion(){
        return new TransaccionDto(gestionDineroController.obtenerNuevoIdTransaccion(), dp_fecha.getValue(),
                Double.parseDouble(tf_monto.getText()), tf_descripcion.getText(),
                cb_tipoMovimiento.getSelectionModel().getSelectedItem(), usuario.idUsuario(),
                cb_cuentaOrigen.getSelectionModel().getSelectedItem(), tf_cuentaDestino.getText(),
                cb_categoria.getSelectionModel().getSelectedItem());
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
        inicializarComboBox();
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
        TipoTransaccion tipoTransaccion = cb_tipoMovimiento.getSelectionModel().getSelectedItem();
        TransaccionDto transaccionDto = crearTransaccion();
        if (tipoTransaccion.equals(TipoTransaccion.DEPOSITO)) {
            if (gestionDineroController.agregarTransaccion(transaccionDto, usuario.idUsuario())){
                System.out.println("Yeih");
                limpiarSeleccion();
            }
        }
        else if (tipoTransaccion.equals(TipoTransaccion.TRANSFERENCIA)) {
            System.out.println();
        }
        else if (tipoTransaccion.equals(TipoTransaccion.RETIRO)) {
            System.out.println();
        }
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
        bt_realizarMovimiento.setDisable(false);
        if (tipoTransaccion == null) {
            cb_cuentaOrigen.setDisable(true);
            tf_cuentaDestino.setDisable(true);
            tf_monto.setDisable(true);
            dp_fecha.setDisable(true);
            tf_descripcion.setDisable(true);
            cb_categoria.setDisable(true);
            bt_realizarMovimiento.setDisable(true);
        }
        else if (tipoTransaccion.equals(TipoTransaccion.DEPOSITO)) {
            tf_cuentaDestino.setDisable(true);
        }
        else if (tipoTransaccion.equals(TipoTransaccion.RETIRO)) {
            tf_cuentaDestino.setDisable(true);
        }
        else {
            tf_cuentaDestino.setDisable(false);
        }
    }

    @FXML
    void initialize() {
        gestionDineroController = new GestionDineroController();
        manejarSeleccionTipoTransaccion();
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