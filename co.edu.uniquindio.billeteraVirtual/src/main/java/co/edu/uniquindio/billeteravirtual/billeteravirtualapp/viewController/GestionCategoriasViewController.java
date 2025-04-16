package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.viewController;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller.GestionCategoriasController;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.CategoriaDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.UsuarioDto;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.BilleteraVirtualConstantes.*;
import static co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.MetodosReutilizables.*;

public class GestionCategoriasViewController {

    private UsuarioDto usuario;
    private GestionCategoriasController gestionCategoriasController;
    ObservableList<CategoriaDto> listaCategorias = FXCollections.observableArrayList();
    CategoriaDto categoriaSeleccionada;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bt_eliminar;

    @FXML
    private TextField tf_nombreCategoria;

    @FXML
    private Label lb_idCategoria;

    @FXML
    private Button bt_actualizar;

    @FXML
    private Label lb_nombreCategoria;

    @FXML
    private Button bt_nuevo;

    @FXML
    private TextField tf_descripcion;

    @FXML
    private TextField tf_idCategoria;

    @FXML
    private Label lb_titulo;

    @FXML
    private TableColumn<CategoriaDto, String> cl_descripcion;

    @FXML
    private TableView<CategoriaDto> tb_categorias;

    @FXML
    private TableColumn<CategoriaDto, String> cl_nombre;

    @FXML
    private Label lb_descripcion;

    @FXML
    private Button bt_limpiar;

    @FXML
    private TableColumn<CategoriaDto, String> cl_presupuestoAsignado;

    @FXML
    private TableColumn<CategoriaDto, Integer> cl_idCategoria;

    @FXML
    void onNuevo() {
        agregarCategoria();
    }

    @FXML
    void onActualizar() {
        actualizarCategoria();
    }

    @FXML
    void onLimpiar() {
        limpiarSeleccion();
    }

    @FXML
    void onEliminar() {
        eliminarCategoria();
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
        initView();
    }

    private CategoriaDto crearCategoria() {
        return new CategoriaDto(Integer.parseInt(tf_idCategoria.getText()), usuario.idUsuario(),
                tf_nombreCategoria.getText(), tf_descripcion.getText(), null);
    }

    private void agregarCategoria() {
        if (verificarCamposLlenos()) {
            if (verificarCamposCorrectos()) {
                CategoriaDto categoria = crearCategoria();
                if (gestionCategoriasController.agregarCategoria(categoria, usuario.idUsuario())) {
                    listaCategorias.add(categoria);
                    tb_categorias.refresh();
                    limpiarSeleccion();
                    mostrarMensaje(TITULO_CATEGORIA_AGREGADA, BODY_CATEGORIA_AGREGADA, Alert.AlertType.INFORMATION);
                }
                else {
                    mostrarMensaje(TITULO_CATEGORIA_NO_AGREGADA, BODY_CATEGORIA_NO_AGREGADA, Alert.AlertType.ERROR);
                }
            }
            else {
                mostrarMensaje(TITULO_INCORRECTO, BODY_INCORRECTO, Alert.AlertType.WARNING);
            }
        }
        else {
            mostrarMensaje(TITULO_CATEGORIA_NO_AGREGADA, BODY_CATEGORIA_NO_AGREGADA, Alert.AlertType.WARNING);
        }
    }

    private void actualizarCategoria() {
        if (categoriaSeleccionada != null) {
            if (verificarCamposLlenos()) {
                if (verificarCamposCorrectos()) {
                    CategoriaDto categoriaNueva = crearCategoria();
                    if (gestionCategoriasController.actualizarCategoria(usuario.idUsuario(),
                            categoriaSeleccionada.idCategoria(), categoriaNueva)){
                        intercambiarCategorias(categoriaSeleccionada.idCategoria(), categoriaNueva);
                        limpiarSeleccion();
                        tb_categorias.refresh();
                        mostrarMensaje(TITULO_CATEGORIA_ACTUALIZADA,
                                BODY_CATEGORIA_ACTUALIZADA, Alert.AlertType.INFORMATION);
                    }
                    else {
                        mostrarMensaje(TITULO_CATEGORIA_NO_ACTUALIZADA,
                                BODY_CATEGORIA_NO_ACTUALIZADA, Alert.AlertType.ERROR);
                    }
                }
                else {
                    mostrarMensaje(TITULO_INCORRECTO, BODY_INCORRECTO, Alert.AlertType.WARNING);
                }
            }
            else {
                mostrarMensaje(TITULO_CATEGORIA_NO_AGREGADA,
                        BODY_CATEGORIA_NO_AGREGADA, Alert.AlertType.WARNING);
            }
        }
        else {
            mostrarMensaje(TITULO_CATEGORIA_NO_SELECCIONADA,
                    BODY_CATEGORIA_NO_SELECCIONADA, Alert.AlertType.WARNING);
        }
    }


    private void eliminarCategoria() {
        if (categoriaSeleccionada != null) {
            if (mostrarMensajeConfirmacion(BODY_CONFIRMACION_ELIMINAR_CATEGORIA) &&
                    gestionCategoriasController.
                            eliminarCategoria(usuario.idUsuario(), categoriaSeleccionada.idCategoria())) {
                listaCategorias.remove(categoriaSeleccionada);
                limpiarSeleccion();
                mostrarMensaje(TITULO_CATEGORIA_ELIMINADA, BODY_CATEGORIA_ELIMINADA, Alert.AlertType.INFORMATION);
            }
        }
        else {
            mostrarMensaje(TITULO_CATEGORIA_NO_SELECCIONADA,
                    BODY_CATEGORIA_NO_SELECCIONADA, Alert.AlertType.WARNING);
        }
    }

    private void intercambiarCategorias(int idCategoria, CategoriaDto categoriaNueva) {
        for (int i = 0; i < listaCategorias.size(); i++) {
            CategoriaDto categoria = listaCategorias.get(i);
            if (categoria.idCategoria() == idCategoria) {
                listaCategorias.set(i, categoriaNueva);
            }
        }
    }

    private boolean verificarCamposLlenos() {
        return !tf_nombreCategoria.getText().isEmpty() &&
                !tf_idCategoria.getText().isEmpty();
    }

    private boolean verificarCamposCorrectos(){
        return isInteger(tf_idCategoria.getText());
    }

    private void mostrarInformacionCategoria(CategoriaDto categoria) {
        if (categoria != null) {
            tf_nombreCategoria.setText(categoria.nombre());
            tf_descripcion.setText(categoria.descripcion());
            tf_idCategoria.setText(String.valueOf(categoria.idCategoria()));
        }
    }

    private void obtenerCategorias() {
        listaCategorias.addAll(gestionCategoriasController.obtenerCategorias(usuario.idUsuario()));
    }

    private void initView() {
        initDataBinding();
        obtenerCategorias();
        tb_categorias.getItems().clear();
        tb_categorias.setItems(listaCategorias);
        listenerSelection();
    }

    private void initDataBinding() {
        cl_nombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
        cl_idCategoria.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().idCategoria()).asObject());
        cl_descripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().descripcion()));
        cl_presupuestoAsignado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombrePresupuesto()));
    }

    private void listenerSelection(){
        tb_categorias.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            categoriaSeleccionada = newSelection;
            mostrarInformacionCategoria(categoriaSeleccionada);
        });
    }

    private void limpiarSeleccion() {
        tb_categorias.getSelectionModel().clearSelection();
        limpiarCamposTexto();
    }

    private void limpiarCamposTexto() {
        tf_nombreCategoria.clear();
        tf_idCategoria.clear();
        tf_descripcion.clear();
    }

    @FXML
    void initialize() {
        gestionCategoriasController = new GestionCategoriasController();
        assert bt_eliminar != null : "fx:id=\"bt_eliminar\" was not injected: check your FXML file 'GestionCategorias.fxml'.";
        assert tf_nombreCategoria != null : "fx:id=\"tf_nombreCategoria\" was not injected: check your FXML file 'GestionCategorias.fxml'.";
        assert lb_idCategoria != null : "fx:id=\"lb_idCategoria\" was not injected: check your FXML file 'GestionCategorias.fxml'.";
        assert bt_actualizar != null : "fx:id=\"bt_actualizar\" was not injected: check your FXML file 'GestionCategorias.fxml'.";
        assert lb_nombreCategoria != null : "fx:id=\"lb_nombreCategoria\" was not injected: check your FXML file 'GestionCategorias.fxml'.";
        assert bt_nuevo != null : "fx:id=\"bt_nuevo\" was not injected: check your FXML file 'GestionCategorias.fxml'.";
        assert tf_descripcion != null : "fx:id=\"tf_descripcion\" was not injected: check your FXML file 'GestionCategorias.fxml'.";
        assert tf_idCategoria != null : "fx:id=\"tf_idCategoria\" was not injected: check your FXML file 'GestionCategorias.fxml'.";
        assert lb_titulo != null : "fx:id=\"lb_titulo\" was not injected: check your FXML file 'GestionCategorias.fxml'.";
        assert cl_descripcion != null : "fx:id=\"cl_descripcion\" was not injected: check your FXML file 'GestionCategorias.fxml'.";
        assert tb_categorias != null : "fx:id=\"tb_categorias\" was not injected: check your FXML file 'GestionCategorias.fxml'.";
        assert cl_nombre != null : "fx:id=\"cl_nombre\" was not injected: check your FXML file 'GestionCategorias.fxml'.";
        assert lb_descripcion != null : "fx:id=\"lb_descripcion\" was not injected: check your FXML file 'GestionCategorias.fxml'.";
        assert bt_limpiar != null : "fx:id=\"bt_limpiar\" was not injected: check your FXML file 'GestionCategorias.fxml'.";
        assert cl_presupuestoAsignado != null : "fx:id=\"cl_presupuestoAsignado\" was not injected: check your FXML file 'GestionCategorias.fxml'.";
        assert cl_idCategoria != null : "fx:id=\"cl_idCategoria\" was not injected: check your FXML file 'GestionCategorias.fxml'.";

    }
}