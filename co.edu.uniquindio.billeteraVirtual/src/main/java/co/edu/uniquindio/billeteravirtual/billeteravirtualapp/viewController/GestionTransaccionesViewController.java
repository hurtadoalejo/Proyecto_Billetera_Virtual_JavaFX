package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.viewController;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller.GestionTransaccionesController;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.TransaccionDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.UsuarioDto;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import static co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.MetodosReutilizables.isDouble;

public class GestionTransaccionesViewController {

    private GestionTransaccionesController gestionTransaccionesController;
    ObservableList<TransaccionDto> listaTransacciones = FXCollections.observableArrayList();
    TransaccionDto transaccionSeleccionada;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cb_categoria;

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
    private TableColumn<TransaccionDto, String> cl_categoria;

    @FXML
    private ComboBox<String> cb_cuentaDestino;

    @FXML
    private Button bt_realizarTransaccion;

    @FXML
    private Button bt_limpiar;

    @FXML
    void onLimpiar() {
        //limpiarSeleccion();
    }

    @FXML
    void onRealizarTransaccion() {
        //realizarMovimiento();
    }

    @FXML
    void initialize() {
        gestionTransaccionesController = new GestionTransaccionesController();
    }

    private void initView() {
        initDataBinding();
        obtenerTransacciones();
        tb_transacciones.getItems().clear();
        tb_transacciones.setItems(listaTransacciones);
        cb_usuario.getItems().clear();
        cb_usuario.getItems().setAll(gestionTransaccionesController.obtenerUsuariosId());
        listenerSelection();
    }

    private void obtenerTransacciones() {
        listaTransacciones.addAll(gestionTransaccionesController.obtenerTransacciones(cb_usuario.getValue()));
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
                cb_categoria.getSelectionModel().getSelectedItem());
    }

    /*private void inicializarComboBox() {
        cb_categoria.getItems().addAll();
        cb_categoria.getItems().addAll(gestionTransaccionesController.obtenerCategoriasNombresDeUsuario(usuario.idUsuario()));
        cb_categoria.setOnAction(event -> manejarSeleccionCategoria());
        cb_cuentaOrigen.setOnAction(event -> manejarSeleccionCuentaOrigen());
        LinkedList<String> listaNumCuentas = gestionTransaccionesUsuarioController.obtenerNumCuentasUsuario(usuario.idUsuario());
        cb_cuentaOrigen.getItems().addAll(listaNumCuentas);
        crearListaFiltrada(listaNumCuentas);
    }*/

    private void limpiarCampos() {
        cb_cuentaOrigen.getSelectionModel().clearSelection();
        dp_fecha.setValue(null);
        cb_cuentaDestino.getSelectionModel().clearSelection();
        tf_monto.clear();
        tf_descripcion.clear();
        cb_usuario.getSelectionModel().clearSelection();
    }

    private boolean verificarCamposLlenos() {
        if (!cb_categoria.getSelectionModel().isEmpty()
                && !cb_cuentaOrigen.getSelectionModel().isEmpty()
                && dp_fecha.getValue() != null
                && !tf_monto.getText().isEmpty()
                && !cb_usuario.getSelectionModel().isEmpty()) {
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
        cl_usuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().idUsuario()));
    }
}