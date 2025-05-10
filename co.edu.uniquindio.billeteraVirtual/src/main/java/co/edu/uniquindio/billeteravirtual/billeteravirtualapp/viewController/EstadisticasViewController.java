package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.viewController;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller.EstadisticasController;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.UsuarioDto;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EstadisticasViewController {

    private EstadisticasController estadisticasController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label txt_correo;

    @FXML
    private Label txt_telefono;

    @FXML
    private Label txt_id;

    @FXML
    private Label txt_saldoPromedio;

    @FXML
    private Label txt_gastoComun;

    @FXML
    private Label txt_nombre;

    @FXML
    void initialize() {
        estadisticasController = new EstadisticasController();
        usuarioConMasTransacciones();
        obtenerSaldoPromedioUsuarios();

    }

    private void usuarioConMasTransacciones() {
        UsuarioDto usuarioDto = estadisticasController.obtenerUsuarioMasTransacciones();
        if (usuarioDto != null) {
            txt_nombre.setText(usuarioDto.nombreCompleto());
            txt_id.setText(usuarioDto.idUsuario());
            txt_correo.setText(usuarioDto.correoElectronico());
            txt_telefono.setText(usuarioDto.numeroTelefono());
        }
    }

    private void obtenerSaldoPromedioUsuarios() {
        double saldoPromedio = estadisticasController.obtenerSaldoPromedioUsuarios();
        if (saldoPromedio > 0) {
            txt_saldoPromedio.setText(String.format("$%,.0f", saldoPromedio));
        } else {
            txt_saldoPromedio.setText("0");
        }
    }
}