package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.viewController;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller.GestionPresupuestoController;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.PresupuestoDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.UsuarioDto;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.MetodosReutilizables.*;
import static co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.BilleteraVirtualConstantes.*;

public class GestionPresupuestosViewController {

    private UsuarioDto usuario;
    private GestionPresupuestoController gestionPresupuestoController;
    ObservableList<PresupuestoDto> listaPresupuestos = FXCollections.observableArrayList();
    PresupuestoDto presupuestoSeleccionado;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bt_eliminar;

    @FXML
    private TableColumn<PresupuestoDto, Double> cl_tope;

    @FXML
    private Button bt_actualizar;

    @FXML
    private ComboBox<String> cb_categoria;

    @FXML
    private TextField tf_idPresupuesto;

    @FXML
    private Label lb_idPresupuesto;

    @FXML
    private Label lb_nombrePresupuesto;

    @FXML
    private Button bt_nuevo;

    @FXML
    private TextField tf_tope;

    @FXML
    private Label lb_titulo;

    @FXML
    private Label lb_tope;

    @FXML
    private TableColumn<PresupuestoDto, String> cl_nombre;

    @FXML
    private TableColumn<PresupuestoDto, Double> cl_montoGastado;

    @FXML
    private TableColumn<PresupuestoDto, Integer> cl_idPresupuesto;

    @FXML
    private TextField tf_nombrePresupuesto;

    @FXML
    private TableView<PresupuestoDto> tb_presupuestos;

    @FXML
    private Button bt_limpiar;

    @FXML
    private Label lb_categoria;

    @FXML
    private TableColumn<PresupuestoDto, String> cl_categoriaAsociada;

    @FXML
    void onNuevo() {
        agregarPresupuesto();
    }

    @FXML
    void onActualizar() {
        actualizarPresupuesto();
    }

    @FXML
    void onLimpiar() {
        limpiarSeleccion();
    }

    @FXML
    void onEliminar() {
        eliminarPresupuesto();
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
        cb_categoria.getItems().addAll(gestionPresupuestoController
                .obtenerCategoriasNombresDeUsuario(usuario.idUsuario()));
        initView();
    }

    private PresupuestoDto crearPresupuesto() {
        return new PresupuestoDto(Integer.parseInt(tf_idPresupuesto.getText()), tf_nombrePresupuesto.getText(),
                Double.parseDouble(tf_tope.getText()), 0, usuario.idUsuario(), cb_categoria.getValue());
    }

    private PresupuestoDto crearPresupuesto(double montoGastado) {
        return new PresupuestoDto(Integer.parseInt(tf_idPresupuesto.getText()), tf_nombrePresupuesto.getText(),
                Double.parseDouble(tf_tope.getText()), montoGastado, usuario.idUsuario(), cb_categoria.getValue());
    }

    private void agregarPresupuesto() {
        if (verificarCamposLlenos()){
            if (verificarCamposCorrectos()) {
                PresupuestoDto presupuestoDto = crearPresupuesto();
                if (gestionPresupuestoController
                        .verificarDisponibilidadCategoria(presupuestoDto.nombreCategoria(), usuario.idUsuario())){
                    if (gestionPresupuestoController.agregarPresupuesto(presupuestoDto)){
                        listaPresupuestos.add(presupuestoDto);
                        tb_presupuestos.refresh();
                        limpiarSeleccion();
                        mostrarMensaje(TITULO_PRESUPUESTO_AGREGADO,
                                BODY_PRESUPUESTO_AGREGADO, Alert.AlertType.INFORMATION);
                    }
                    else {
                        mostrarMensaje(TITULO_PRESUPUESTO_NO_AGREGADO,
                                BODY_PRESUPUESTO_NO_AGREGADO, Alert.AlertType.ERROR);
                    }
                }
                else {
                    mostrarMensaje(TITULO_CATEGORIA_NO_DISPONIBLE,
                            BODY_CATEGORIA_NO_DISPONIBLE, Alert.AlertType.ERROR);
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

    private void actualizarPresupuesto() {
        if (presupuestoSeleccionado != null) {
            if (verificarCamposLlenos()){
                if (verificarCamposCorrectos()) {
                    PresupuestoDto presupuestoNuevo = crearPresupuesto(presupuestoSeleccionado.montoGastado());
                    if (verificarTopeCorrecto(presupuestoNuevo)){
                        if (presupuestoSeleccionado.nombreCategoria()
                                .equalsIgnoreCase(presupuestoNuevo.nombreCategoria()) ||
                                gestionPresupuestoController.verificarDisponibilidadCategoria
                                (presupuestoNuevo.nombreCategoria(), usuario.idUsuario())){
                            if (gestionPresupuestoController.actualizarPresupuesto
                                    (presupuestoSeleccionado.idPresupuesto(), presupuestoNuevo)){
                                intercambiarPresupuestos(presupuestoSeleccionado.idPresupuesto(), presupuestoNuevo);
                                limpiarSeleccion();
                                tb_presupuestos.refresh();
                                mostrarMensaje(TITULO_PRESUPUESTO_ACTUALIZADO,
                                        BODY_PRESUPUESTO_ACTUALIZADO, Alert.AlertType.INFORMATION);
                            }
                            else {
                                mostrarMensaje(TITULO_PRESUPUESTO_NO_ACTUALIZADO,
                                        BODY_PRESUPUESTO_NO_ACTUALIZADO, Alert.AlertType.ERROR);
                            }
                        }
                        else {
                            mostrarMensaje(TITULO_CATEGORIA_NO_DISPONIBLE,
                                    BODY_CATEGORIA_NO_DISPONIBLE, Alert.AlertType.ERROR);
                        }
                    }
                    else {
                        mostrarMensaje(TITULO_PRESUPUESTO_TOPE_INVALIDO,
                                BODY_PRESUPUESTO_TOPE_INVALIDO, Alert.AlertType.ERROR);
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
            mostrarMensaje(TITULO_PRESUPUESTO_NO_SELECCIONADO,
                    BODY_PRESUPUESTO_NO_SELECCIONADO, Alert.AlertType.WARNING);
        }
    }

    private void eliminarPresupuesto() {
        if (presupuestoSeleccionado != null) {
            if (mostrarMensajeConfirmacion(BODY_CONFIRMACION_ELIMINAR_PRESUPUESTO) && gestionPresupuestoController
                    .eliminarPresupuesto(presupuestoSeleccionado.idPresupuesto(), usuario.idUsuario())){
                listaPresupuestos.remove(presupuestoSeleccionado);
                limpiarSeleccion();
                mostrarMensaje(TITULO_PRESUPUESTO_ELIMINADO,
                        BODY_PRESUPUESTO_ELIMINADO, Alert.AlertType.INFORMATION);
            }
        }
        else {
            mostrarMensaje(TITULO_PRESUPUESTO_NO_SELECCIONADO,
                    BODY_PRESUPUESTO_NO_SELECCIONADO, Alert.AlertType.WARNING);
        }
    }

    private void intercambiarPresupuestos(int idPresupuesto, PresupuestoDto presupuestoNuevo) {
        for (int i = 0; i < listaPresupuestos.size(); i++) {
            PresupuestoDto presupuesto = listaPresupuestos.get(i);
            if (presupuesto.idPresupuesto() == idPresupuesto) {
                listaPresupuestos.set(i, presupuestoNuevo);
            }
        }
    }

    private boolean verificarTopeCorrecto(PresupuestoDto presupuesto) {
        return presupuesto.montoTotalAsignado() >= presupuestoSeleccionado.montoGastado();
    }

    private boolean verificarCamposLlenos() {
        return !tf_nombrePresupuesto.getText().isEmpty() &&
                !tf_tope.getText().isEmpty() &&
                !tf_idPresupuesto.getText().isEmpty() &&
                !cb_categoria.getSelectionModel().isEmpty();
    }

    private boolean verificarCamposCorrectos(){
        return isInteger(tf_idPresupuesto.getText()) && isDouble(tf_tope.getText());
    }

    private void mostrarInformacionPresupuesto(PresupuestoDto presupuesto) {
        if (presupuesto != null) {
            tf_nombrePresupuesto.setText(presupuesto.nombre());
            tf_idPresupuesto.setText(String.valueOf(presupuesto.idPresupuesto()));
            tf_tope.setText(String.valueOf(presupuesto.montoTotalAsignado()));
            cb_categoria.getSelectionModel().select(presupuesto.nombreCategoria());
        }
    }

    private void obtenerPresupuestos() {
        listaPresupuestos.addAll(gestionPresupuestoController.obtenerPresupuestos(usuario.idUsuario()));
    }

    private void initView() {
        initDataBinding();
        obtenerPresupuestos();
        tb_presupuestos.getItems().clear();
        tb_presupuestos.setItems(listaPresupuestos);
        listenerSelection();
    }

    private void initDataBinding() {
        cl_nombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
        cl_idPresupuesto.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().idPresupuesto()).asObject());
        cl_tope.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().montoTotalAsignado()).asObject());
        cl_montoGastado.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().montoGastado()).asObject());
        cl_categoriaAsociada.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreCategoria()));
    }

    private void listenerSelection(){
        tb_presupuestos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            presupuestoSeleccionado = newSelection;
            mostrarInformacionPresupuesto(presupuestoSeleccionado);
        });
    }

    private void limpiarSeleccion() {
        tb_presupuestos.getSelectionModel().clearSelection();
        limpiarCamposTexto();
    }

    private void limpiarCamposTexto() {
        tf_nombrePresupuesto.clear();
        tf_idPresupuesto.clear();
        tf_tope.clear();
        cb_categoria.getSelectionModel().clearSelection();
    }

    @FXML
    void initialize() {
        gestionPresupuestoController = new GestionPresupuestoController();
        assert bt_eliminar != null : "fx:id=\"bt_eliminar\" was not injected: check your FXML file 'GestionPresupuestos.fxml'.";
        assert cl_tope != null : "fx:id=\"cl_tope\" was not injected: check your FXML file 'GestionPresupuestos.fxml'.";
        assert bt_actualizar != null : "fx:id=\"bt_actualizar\" was not injected: check your FXML file 'GestionPresupuestos.fxml'.";
        assert cb_categoria != null : "fx:id=\"cb_categoria\" was not injected: check your FXML file 'GestionPresupuestos.fxml'.";
        assert tf_idPresupuesto != null : "fx:id=\"tf_idPresupuesto\" was not injected: check your FXML file 'GestionPresupuestos.fxml'.";
        assert lb_idPresupuesto != null : "fx:id=\"lb_idPresupuesto\" was not injected: check your FXML file 'GestionPresupuestos.fxml'.";
        assert lb_nombrePresupuesto != null : "fx:id=\"lb_nombrePresupuesto\" was not injected: check your FXML file 'GestionPresupuestos.fxml'.";
        assert bt_nuevo != null : "fx:id=\"bt_nuevo\" was not injected: check your FXML file 'GestionPresupuestos.fxml'.";
        assert tf_tope != null : "fx:id=\"tf_tope\" was not injected: check your FXML file 'GestionPresupuestos.fxml'.";
        assert lb_titulo != null : "fx:id=\"lb_titulo\" was not injected: check your FXML file 'GestionPresupuestos.fxml'.";
        assert lb_tope != null : "fx:id=\"lb_tope\" was not injected: check your FXML file 'GestionPresupuestos.fxml'.";
        assert cl_nombre != null : "fx:id=\"cl_nombre\" was not injected: check your FXML file 'GestionPresupuestos.fxml'.";
        assert cl_montoGastado != null : "fx:id=\"cl_montoGastado\" was not injected: check your FXML file 'GestionPresupuestos.fxml'.";
        assert cl_idPresupuesto != null : "fx:id=\"cl_idPresupuesto\" was not injected: check your FXML file 'GestionPresupuestos.fxml'.";
        assert tf_nombrePresupuesto != null : "fx:id=\"tf_nombrePresupuesto\" was not injected: check your FXML file 'GestionPresupuestos.fxml'.";
        assert tb_presupuestos != null : "fx:id=\"tb_presupuestos\" was not injected: check your FXML file 'GestionPresupuestos.fxml'.";
        assert bt_limpiar != null : "fx:id=\"bt_limpiar\" was not injected: check your FXML file 'GestionPresupuestos.fxml'.";
        assert lb_categoria != null : "fx:id=\"lb_categoria\" was not injected: check your FXML file 'GestionPresupuestos.fxml'.";
        assert cl_categoriaAsociada != null : "fx:id=\"cl_categoriaAsociada\" was not injected: check your FXML file 'GestionPresupuestos.fxml'.";
    }
}