package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.viewController;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller.GestionUsuariosController;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.UsuarioDto;
import static co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.BilleteraVirtualConstantes.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class GestionUsuariosViewController {

    private GestionUsuariosController gestionUsuariosController;
    ObservableList<UsuarioDto> listaUsuarios = FXCollections.observableArrayList();
    UsuarioDto usuarioSeleccionado;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bt_eliminar;

    @FXML
    private TableColumn<UsuarioDto, String> cl_nombreCompleto;

    @FXML
    private Button bt_actualizar;

    @FXML
    private TableColumn<UsuarioDto, Integer> cl_clave;

    @FXML
    private TextField tf_correo;

    @FXML
    private TableColumn<UsuarioDto, String> cl_correo;

    @FXML
    private Button bt_nuevo;

    @FXML
    private Label lb_direccion;

    @FXML
    private TextField tf_telefono;

    @FXML
    private Label lb_titulo;

    @FXML
    private TableColumn<UsuarioDto, String> cl_telefono;

    @FXML
    private Label lb_nombre;

    @FXML
    private TextField tf_direccion;

    @FXML
    private TableColumn<UsuarioDto, String> cl_id;

    @FXML
    private TextField tf_nombre;

    @FXML
    private TextField tf_id;

    @FXML
    private TableView<UsuarioDto> tb_usuarios;

    @FXML
    private Label lb_id;

    @FXML
    private Label lb_telefono;

    @FXML
    private Label lb_correo;

    @FXML
    private Button bt_limpiar;

    @FXML
    private Label lb_clave;

    @FXML
    private TableColumn<UsuarioDto, String> cl_direccion;

    @FXML
    private TextField tf_clave;

    @FXML
    void onNuevo() {
        agregarUsuario();
    }

    @FXML
    void onActualizar() {
        actualizarUsuario();
    }

    @FXML
    void onLimpiar() {
        limpiarSeleccion();
    }

    @FXML
    void onEliminar() {
        eliminarUsuario();
    }

    private UsuarioDto crearUsuario() {
        return new UsuarioDto(tf_nombre.getText(), tf_id.getText(), tf_correo.getText(), tf_telefono.getText(),
                tf_direccion.getText(), Integer.parseInt(tf_clave.getText()));
    }

    private void agregarUsuario() {
        if (verificarCamposLlenos()) {
            if (verificarCamposCorrectos()){
                UsuarioDto usuario = crearUsuario();
                if (gestionUsuariosController.agregarUsuario(usuario)) {
                    listaUsuarios.add(usuario);
                    tb_usuarios.refresh();
                    limpiarSeleccion();
                    mostrarMensaje(TITULO_USUARIO_AGREGADO, HEADER, BODY_USUARIO_AGREGADO, Alert.AlertType.INFORMATION);
                }
                else{
                    mostrarMensaje(TITULO_USUARIO_NO_AGREGADO, HEADER, BODY_USUARIO_NO_AGREGADO, Alert.AlertType.ERROR);
                }
            }
            else{
                mostrarMensaje(TITULO_INCORRECTO, HEADER, BODY_INCORRECTO, Alert.AlertType.WARNING);
            }
        }
        else{
            mostrarMensaje(TITULO_INCOMPLETO, HEADER, BODY_INCOMPLETO, Alert.AlertType.WARNING);
        }
    }

    private void actualizarUsuario() {
        if (usuarioSeleccionado != null) {
            if (verificarCamposLlenos()) {
                if (verificarCamposCorrectos()){
                    UsuarioDto usuarioNuevo = crearUsuario();
                    if (gestionUsuariosController.actualizarUsuario(usuarioSeleccionado.idUsuario(), usuarioNuevo)) {
                        intercambiarUsuarios(usuarioSeleccionado.idUsuario(), usuarioNuevo);
                        limpiarSeleccion();
                        tb_usuarios.refresh();
                        mostrarMensaje(TITULO_USUARIO_ACTUALIZADO, HEADER, BODY_USUARIO_ACTUALIZADO, Alert.AlertType.INFORMATION);
                    }
                    else{
                        mostrarMensaje(TITULO_USUARIO_NO_ACTUALIZADO, HEADER, BODY_USUARIO_NO_ACTUALIZADO, Alert.AlertType.ERROR);
                    }
                }
                else{
                    mostrarMensaje(TITULO_INCORRECTO, HEADER, BODY_INCORRECTO, Alert.AlertType.WARNING);
                }
            }
            else{
                mostrarMensaje(TITULO_INCOMPLETO, HEADER, BODY_INCOMPLETO, Alert.AlertType.WARNING);
            }
        }
        else {
            mostrarMensaje(TITULO_USUARIO_NO_SELECCIONADO, HEADER, BODY_USUARIO_NO_SELECCIONADO, Alert.AlertType.WARNING);
        }
    }

    private void intercambiarUsuarios(String id, UsuarioDto usuarioNuevo) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            UsuarioDto usuario = listaUsuarios.get(i);
            if (usuario.idUsuario().equals(id)) {
                listaUsuarios.set(i, usuarioNuevo);
            }
        }
    }

    private void eliminarUsuario() {
        if (usuarioSeleccionado != null) {
            if (mostrarMensajeConfirmacion(BODY_CONFIRMACION_ELIMINAR_USUARIO) && gestionUsuariosController.eliminarUsuario(usuarioSeleccionado.idUsuario())){
                listaUsuarios.remove(usuarioSeleccionado);
                limpiarSeleccion();
                mostrarMensaje(TITULO_USUARIO_ELIMINADO, HEADER, BODY_USUARIO_ELIMINADO, Alert.AlertType.INFORMATION);
            }
        }
        else{
            mostrarMensaje(TITULO_USUARIO_NO_SELECCIONADO, HEADER, BODY_USUARIO_NO_SELECCIONADO, Alert.AlertType.WARNING);
        }
    }

    private boolean verificarCamposLlenos() {
        if (tf_correo.getText().isEmpty() || tf_telefono.getText().isEmpty() || tf_direccion.getText().isEmpty()
        || tf_clave.getText().isEmpty() || tf_id.getText().isEmpty() || tf_nombre.getText().isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean verificarCamposCorrectos(){
        if (isInteger(tf_clave.getText()) && isLong(tf_telefono.getText()) &&
        isLong(tf_id.getText()) && esCorreoValido(tf_correo.getText())) {
            return true;
        }
        return false;
    }

    public static boolean esCorreoValido(String correo) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return correo.matches(regex);
    }

    private void mostrarInformacionUsuario(UsuarioDto usuario) {
        if (usuario != null) {
            tf_nombre.setText(usuario.nombreCompleto());
            tf_id.setText(usuario.idUsuario());
            tf_telefono.setText(usuario.numeroTelefono());
            tf_correo.setText(usuario.correoElectronico());
            tf_direccion.setText(usuario.direccion());
            tf_clave.setText(String.valueOf(usuario.clave()));
        }
    }

    private void obtenerUsuarios() {
        listaUsuarios.addAll(gestionUsuariosController.obtenerUsuarios());
    }

    private void initView() {
        initDataBinding();
        obtenerUsuarios();
        tb_usuarios.getItems().clear();
        tb_usuarios.setItems(listaUsuarios);
        listenerSelection();
    }

    private void initDataBinding() {
        cl_nombreCompleto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreCompleto()));
        cl_id.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().idUsuario()));
        cl_telefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().numeroTelefono()));
        cl_correo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().correoElectronico()));
        cl_direccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().direccion()));
        cl_clave.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().clave()).asObject());
    }

    private void listenerSelection(){
        tb_usuarios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            usuarioSeleccionado = newSelection;
            mostrarInformacionUsuario(usuarioSeleccionado);
        });
    }

    private void limpiarSeleccion() {
        tb_usuarios.getSelectionModel().clearSelection();
        limpiarCamposTexto();
    }

    private void limpiarCamposTexto() {
        tf_correo.clear();
        tf_telefono.clear();
        tf_direccion.clear();
        tf_clave.clear();
        tf_id.clear();
        tf_nombre.clear();
    }

    private boolean isInteger(String text){
        if (text == null || text.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isLong(String text){
        if (text == null || text.isEmpty()) {
            return false;
        }
        try {
            Long.parseLong(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    private boolean mostrarMensajeConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmacion");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        }
        return false;
    }

    @FXML
    void initialize() {
        gestionUsuariosController = new GestionUsuariosController();
        initView();
        assert bt_eliminar != null : "fx:id=\"bt_eliminar\" was not injected: check your FXML file 'GestionUsuarios.fxml'.";
        assert cl_nombreCompleto != null : "fx:id=\"cl_nombreCompleto\" was not injected: check your FXML file 'GestionUsuarios.fxml'.";
        assert bt_actualizar != null : "fx:id=\"bt_actualizar\" was not injected: check your FXML file 'GestionUsuarios.fxml'.";
        assert cl_clave != null : "fx:id=\"cl_clave\" was not injected: check your FXML file 'GestionUsuarios.fxml'.";
        assert tf_correo != null : "fx:id=\"tf_correo\" was not injected: check your FXML file 'GestionUsuarios.fxml'.";
        assert cl_correo != null : "fx:id=\"cl_correo\" was not injected: check your FXML file 'GestionUsuarios.fxml'.";
        assert bt_nuevo != null : "fx:id=\"bt_nuevo\" was not injected: check your FXML file 'GestionUsuarios.fxml'.";
        assert lb_direccion != null : "fx:id=\"lb_direccion\" was not injected: check your FXML file 'GestionUsuarios.fxml'.";
        assert tf_telefono != null : "fx:id=\"tf_telefono\" was not injected: check your FXML file 'GestionUsuarios.fxml'.";
        assert cl_telefono != null : "fx:id=\"cl_telefono\" was not injected: check your FXML file 'GestionUsuarios.fxml'.";
        assert lb_nombre != null : "fx:id=\"lb_nombre\" was not injected: check your FXML file 'GestionUsuarios.fxml'.";
        assert tf_direccion != null : "fx:id=\"tf_direccion\" was not injected: check your FXML file 'GestionUsuarios.fxml'.";
        assert cl_id != null : "fx:id=\"cl_id\" was not injected: check your FXML file 'GestionUsuarios.fxml'.";
        assert tf_nombre != null : "fx:id=\"tf_nombre\" was not injected: check your FXML file 'GestionUsuarios.fxml'.";
        assert tf_id != null : "fx:id=\"tf_id\" was not injected: check your FXML file 'GestionUsuarios.fxml'.";
        assert tb_usuarios != null : "fx:id=\"tb_usuarios\" was not injected: check your FXML file 'GestionUsuarios.fxml'.";
        assert lb_id != null : "fx:id=\"lb_id\" was not injected: check your FXML file 'GestionUsuarios.fxml'.";
        assert lb_telefono != null : "fx:id=\"lb_telefono\" was not injected: check your FXML file 'GestionUsuarios.fxml'.";
        assert lb_correo != null : "fx:id=\"lb_correo\" was not injected: check your FXML file 'GestionUsuarios.fxml'.";
        assert bt_limpiar != null : "fx:id=\"bt_limpiar\" was not injected: check your FXML file 'GestionUsuarios.fxml'.";
        assert lb_clave != null : "fx:id=\"lb_clave\" was not injected: check your FXML file 'GestionUsuarios.fxml'.";
        assert cl_direccion != null : "fx:id=\"cl_direccion\" was not injected: check your FXML file 'GestionUsuarios.fxml'.";
        assert tf_clave != null : "fx:id=\"tf_clave\" was not injected: check your FXML file 'GestionUsuarios.fxml'.";
        assert lb_titulo != null : "fx:id=\"lb_titulo\" was not injected: check your FXML file 'GestionUsuarios.fxml'.";
    }
}