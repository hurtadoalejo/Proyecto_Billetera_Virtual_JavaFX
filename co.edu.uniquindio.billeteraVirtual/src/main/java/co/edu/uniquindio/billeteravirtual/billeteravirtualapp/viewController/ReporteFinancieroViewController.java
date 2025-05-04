package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.viewController;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller.ReporteFinancieroController;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.TransaccionDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.UsuarioDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.GeneradorPDF;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class ReporteFinancieroViewController {

    private UsuarioDto usuario;
    ReporteFinancieroController reporteFinancieroController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bt_generarReporte;

    @FXML
    private ComboBox<String> cb_tipoReporte;

    @FXML
    void onGenerarReporte() {
        generarReporte();
    }

    @FXML
    void initialize() {
        reporteFinancieroController = new ReporteFinancieroController();
    }

    private void generarReporte() {
        String tipoReporte = cb_tipoReporte.getSelectionModel().getSelectedItem();
        if (tipoReporte != null) {
            GeneradorPDF.exportarTransacciones(usuario.idUsuario(), tipoReporte, obtenerListaTransaccionesIngresos());
        } else {
            System.out.println();
        }
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
        cb_tipoReporte.getItems().addAll(reporteFinancieroController.obtenerCategoriasPorNombreUsuario(usuario.idUsuario()));
    }

    private LinkedList<TransaccionDto> obtenerListaTransaccionesGastos() {
        return reporteFinancieroController.obtenerListaTransaccionesGastos(usuario.idUsuario());
    }

    private LinkedList<TransaccionDto> obtenerListaTransaccionesIngresos() {
        return reporteFinancieroController.obtenerListaTransaccionesIngresos(usuario.idUsuario());
    }
}