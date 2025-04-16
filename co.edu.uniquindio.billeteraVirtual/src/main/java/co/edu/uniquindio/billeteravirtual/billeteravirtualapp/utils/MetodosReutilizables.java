package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

import static co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.BilleteraVirtualConstantes.HEADER;

public class MetodosReutilizables {

    public static boolean mostrarMensajeConfirmacion(String mensaje) {
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

    public static void mostrarMensaje(String titulo, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(HEADER);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    public static boolean isInteger(String text){
        if (text == null || text.isEmpty()) {
            return false;
        }
        try {
            int numero = Integer.parseInt(text);
            return numero > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isLong(String text){
        if (text == null || text.isEmpty()) {
            return false;
        }
        try {
            long numero = Long.parseLong(text);
            return numero > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDouble(String text){
        if (text == null || text.isEmpty()) {
            return false;
        }
        try {
            double numero = Double.parseDouble(text);
            return numero > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean esCorreoValido(String correo) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return correo.matches(regex);
    }
}