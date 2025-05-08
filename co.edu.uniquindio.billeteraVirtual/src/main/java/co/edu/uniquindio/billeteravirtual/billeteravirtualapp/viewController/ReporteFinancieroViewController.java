package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.viewController;

import java.net.URL;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.ResourceBundle;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.controller.ReporteFinancieroController;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.CuentaDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.TransaccionDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.UsuarioDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.GeneradorPDF;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import static co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.BilleteraVirtualConstantes.*;
import static co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.MetodosReutilizables.*;

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
    private DatePicker dp_fechaFin;

    @FXML
    private DatePicker dp_fechaInicio;

    @FXML
    private Button bt_limpiar;

    @FXML
    private ComboBox<String> cb_tipoReporte;

    @FXML
    void onGenerarReporte() {
        generarReporte();
    }

    @FXML
    void onLimpiar() {
        limpiarCampos();
    }

    @FXML
    void initialize() {
        reporteFinancieroController = new ReporteFinancieroController();
        limpiarCampos();
        listenerTipoReporte();
        dp_fechaInicio.setDisable(true);
        dp_fechaFin.setDisable(true);
    }

    private void generarReporte() {
        if (verificarCamposLlenos()) {
            String tipoReporte = cb_tipoReporte.getValue();
            if (verificarFechasCorrectas()) {
                LocalDate fechaInicio = dp_fechaInicio.getValue();
                LocalDate fechaFin = dp_fechaFin.getValue();
                if (tipoReporte.equals("Ingresos")) {
                    GeneradorPDF.exportarTransacciones(usuario, tipoReporte,
                            obtenerListaTransaccionesIngresos(fechaInicio, fechaFin),
                            null, fechaInicio, fechaFin);
                } else if (tipoReporte.equals("Saldos")) {
                    GeneradorPDF.exportarTransacciones(usuario, tipoReporte, null,
                            obtenerListaCuentas(), null, null);
                } else if (tipoReporte.equals("Gastos")) {
                    GeneradorPDF.exportarTransacciones(usuario, tipoReporte,
                            obtenerListaTransaccionesGastos(fechaInicio, fechaFin),
                            null, fechaInicio, fechaFin);
                }
            } else {
                mostrarMensaje(TITULO_FECHAS_INVALIDAS, BODY_FECHAS_INVALIDAS, Alert.AlertType.WARNING);
            }
        } else {
            mostrarMensaje(TITULO_INCOMPLETO, BODY_INCOMPLETO, Alert.AlertType.WARNING);
        }
    }

    private boolean verificarCamposLlenos() {
        String tipoTransaccion = cb_tipoReporte.getValue();
        if (tipoTransaccion != null) {
            if (tipoTransaccion.equals("Saldos")) {
                return true;
            } else {
                return dp_fechaInicio.getValue() != null && dp_fechaFin.getValue() != null;
            }
        }
        return false;
    }

    private boolean verificarFechasCorrectas() {
        if (!cb_tipoReporte.getValue().equals("Saldos")) {
            LocalDate fechaInicio = dp_fechaInicio.getValue();
            LocalDate fechaFin = dp_fechaFin.getValue();
            return !fechaFin.isBefore(fechaInicio);
        }
        return true;
    }

    private void limpiarCampos(){
        dp_fechaInicio.setValue(null);
        dp_fechaFin.setValue(null);
        cb_tipoReporte.getSelectionModel().clearSelection();
    }

    private void listenerTipoReporte() {
        cb_tipoReporte.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null && !newSelection.equals("Saldos"))  {
                dp_fechaInicio.setDisable(false);
                dp_fechaFin.setDisable(false);
            } else {
                dp_fechaInicio.setDisable(true);
                dp_fechaFin.setDisable(true);
                dp_fechaFin.setValue(null);
                dp_fechaInicio.setValue(null);
            }
        });
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
        cb_tipoReporte.getItems().addAll("Ingresos", "Gastos", "Saldos");
    }

    private LinkedList<TransaccionDto> obtenerListaTransaccionesGastos(LocalDate fechaInicio, LocalDate fechaFin) {
        return reporteFinancieroController.obtenerListaTransaccionesGastos(usuario.idUsuario(), fechaInicio, fechaFin);
    }

    private LinkedList<TransaccionDto> obtenerListaTransaccionesIngresos(LocalDate fechaInicio, LocalDate fechaFin) {
        return reporteFinancieroController.obtenerListaTransaccionesIngresos(usuario.idUsuario(), fechaInicio, fechaFin);
    }

    private LinkedList<CuentaDto> obtenerListaCuentas() {
        return reporteFinancieroController.obtenerListaCuentas(usuario.idUsuario());
    }
}