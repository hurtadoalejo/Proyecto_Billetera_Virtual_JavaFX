package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.viewController;

import java.net.URL;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller.GestionTransaccionesUsuarioController;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.TransaccionDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.UsuarioDto;
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
import static co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.MetodosReutilizables.*;

public class GestionTransaccionesUsuarioViewController {

    private UsuarioDto usuario;
    private GestionTransaccionesUsuarioController gestionTransaccionesUsuarioController;
    private TipoTransaccion tipoSeleccionado;
    private String categoriaSeleccionada;
    private LocalDate fechaDesdeSeleccionada;
    private LocalDate fechaHastaSeleccionada;
    private FilteredList<TransaccionDto> listaFiltrada;
    ObservableList<TransaccionDto> listaTransacciones = FXCollections.observableArrayList();
    TransaccionDto transaccionSeleccionada;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bt_actualizar;

    @FXML
    private TableColumn<TransaccionDto, String> cl_cuentaOrigen;

    @FXML
    private TableColumn<TransaccionDto, String> cl_cuentaDestino;

    @FXML
    private ComboBox<TipoTransaccion> cb_tipoTransaccion;

    @FXML
    private TableColumn<TransaccionDto, String> cl_tipoTransaccion;

    @FXML
    private ComboBox<String> cb_CategoriaFiltrar;

    @FXML
    private ComboBox<String> cb_CategoriaAsignar;

    @FXML
    private DatePicker datePicker_FechaFin;

    @FXML
    private TableColumn<TransaccionDto, Integer> cl_idTransaccion;

    @FXML
    private TableColumn<TransaccionDto, String> cl_descripcion;

    @FXML
    private TableColumn<TransaccionDto, Double> cl_monto;

    @FXML
    private TableView<TransaccionDto> tb_transacciones;

    @FXML
    private DatePicker datePicker_FechaInicio;

    @FXML
    private TableColumn<TransaccionDto, String> cl_fecha;

    @FXML
    private TableColumn<TransaccionDto, String> cl_categoria;

    @FXML
    private Button bt_limpiar;

    @FXML
    void onLimpiar() {
        limpiarSeleccion();
    }

    @FXML
    void onActualizar() {
        System.out.println();
    }

    @FXML
    void initialize() {
        gestionTransaccionesUsuarioController = new GestionTransaccionesUsuarioController();
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
        configurarComboBox();
        initView();
        crearListaFiltrada();
    }

    private void crearListaFiltrada() {
        listaFiltrada = new FilteredList<>(listaTransacciones, p -> true);
        tb_transacciones.setItems(listaFiltrada);
        listenerComboBoxTipo();
        listenerComboBoxCategoria();
        listenerFechaDesde();
        listenerFechaHasta();
    }

    private void listenerComboBoxTipo() {
        cb_tipoTransaccion.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
           tipoSeleccionado = newSelection;
            aplicarFiltro();
        });
    }

    private void listenerComboBoxCategoria() {
        cb_CategoriaFiltrar.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            categoriaSeleccionada = newSelection;
            aplicarFiltro();
        });
    }

    private void listenerFechaDesde() {
        datePicker_FechaInicio.valueProperty().addListener((obs, oldDate, newDate) -> {
            fechaDesdeSeleccionada = newDate;
            aplicarFiltro();
        });
    }

    private void listenerFechaHasta() {
        datePicker_FechaFin.valueProperty().addListener((obs, oldDate, newDate) -> {
            fechaHastaSeleccionada = newDate;
            aplicarFiltro();
        });
    }

    private void aplicarFiltro() {
        listaFiltrada.setPredicate(transaccionDto -> {

            boolean coincideTipo = (tipoSeleccionado == null ||
                    transaccionDto.tipoTransaccion().name().equals(tipoSeleccionado.name()));

            boolean coincideFechaDesde = (fechaDesdeSeleccionada == null ||
                    !transaccionDto.fecha().isBefore(fechaDesdeSeleccionada));

            boolean coincideFechaHasta = (fechaHastaSeleccionada == null ||
                    !transaccionDto.fecha().isAfter(fechaHastaSeleccionada));

            boolean coincideCategoria;
            if (categoriaSeleccionada == null) {
                coincideCategoria = true;
            } else if (categoriaSeleccionada.equalsIgnoreCase("Sin categoría asignada")) {
                coincideCategoria = transaccionDto.nombreCategoria() == null;
            } else {
                coincideCategoria = categoriaSeleccionada.equals(transaccionDto.nombreCategoria());
            }

            return coincideTipo && coincideCategoria && coincideFechaDesde && coincideFechaHasta;
        });
    }

    private void configurarComboBox() {
        cb_tipoTransaccion.getItems().addAll(TipoTransaccion.values());
        LinkedList<String> listaCategorias =
                gestionTransaccionesUsuarioController.obtenerCategoriasPorNombreUsuario(usuario.idUsuario());
        cb_CategoriaFiltrar.getItems().add("Sin categoría asignada");
        cb_CategoriaFiltrar.getItems().addAll(listaCategorias);
        cb_CategoriaAsignar.getItems().add("Sin categoría asignada");
        cb_CategoriaAsignar.getItems().addAll(listaCategorias);
    }

    private TransaccionDto crearTransaccion(TransaccionDto transaccionDto) {
        return new TransaccionDto(
                transaccionDto.idTransaccion(),
                transaccionDto.fecha(),
                transaccionDto.monto(),
                transaccionDto.descripcion(),
                transaccionDto.tipoTransaccion(),
                transaccionDto.idUsuario(),
                transaccionDto.numCuentaOrigen(),
                transaccionDto.numCuentaDestino(),
                obtenerNombreCategoria());
    }

    private String obtenerNombreCategoria() {
        String categoriaSeleccionada = cb_CategoriaAsignar.getValue();
        return (categoriaSeleccionada == null ||
                categoriaSeleccionada.isEmpty()) ||
                categoriaSeleccionada.equalsIgnoreCase("Sin categoría asignada")
                ? null : categoriaSeleccionada;
    }

    private boolean validarSeleccionCategoria(TransaccionDto transaccionDto) {
        if (transaccionDto.nombreCategoria() == null &&
                cb_CategoriaAsignar.getValue().equalsIgnoreCase("Sin categoría asignada")) {
            return false;
        }
        else if (transaccionDto.nombreCategoria() != null &&
                cb_CategoriaAsignar.getValue().equalsIgnoreCase(transaccionDto.nombreCategoria())) {
            return false;
        }
        return true;
    }

    private boolean transaccionPasaPresupuesto(TransaccionDto transaccion) {
        if (transaccion.tipoTransaccion() != TipoTransaccion.DEPOSITO) {
            if (transaccion.nombreCategoria() != null) {
                return gestionTransaccionesUsuarioController.transaccionPasaPresupuesto(usuario.idUsuario(), transaccion);
            }
        }
        return true;
    }

    private void intercambiarTransacciones(TransaccionDto transaccionSeleccionada, TransaccionDto transaccionDto) {
        int indiceTransaccion = listaTransacciones.indexOf(transaccionSeleccionada);
        listaTransacciones.set(indiceTransaccion, transaccionDto);
    }

    private void limpiarSeleccion() {
        datePicker_FechaInicio.setValue(null);
        datePicker_FechaFin.setValue(null);
        cb_CategoriaFiltrar.setValue(null);
        cb_CategoriaAsignar.setValue(null);
        cb_tipoTransaccion.setValue(null);
        tb_transacciones.getSelectionModel().clearSelection();
    }

    private void mostrarCategoriaTransaccion(TransaccionDto transaccion) {
        if (transaccion != null) {
            String categoria = transaccion.nombreCategoria();
            if (categoria != null) {
                cb_CategoriaAsignar.setValue(categoria);
            } else {
                cb_CategoriaAsignar.setValue("Sin categoría asignada");
            }
        }
    }

    private void obtenerTransacciones() {
        listaTransacciones.addAll(gestionTransaccionesUsuarioController.obtenerTransacciones(usuario.idUsuario()));
    }

    private void initView() {
        initDataBinding();
        obtenerTransacciones();
        tb_transacciones.getItems().clear();
        tb_transacciones.setItems(listaTransacciones);
        listenerSelection();
    }

    private void initDataBinding() {
        cl_idTransaccion.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().idTransaccion()).asObject());
        cl_fecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().fecha().toString()));
        cl_monto.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().monto()).asObject());
        cl_descripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().descripcion()));
        cl_cuentaDestino.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().numCuentaDestino()));
        cl_cuentaOrigen.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().numCuentaOrigen()));
        cl_categoria.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreCategoria()));
        cl_tipoTransaccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().tipoTransaccion().name()));
    }

    private void listenerSelection(){
        tb_transacciones.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            transaccionSeleccionada = newSelection;
            mostrarCategoriaTransaccion(transaccionSeleccionada);
        });
    }
}