package co.edu.uniquindio.billeteravirtual.billeteravirtualapp;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.factory.ModelFactory;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.viewController.PrincipalViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

import static co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils.MetodosReutilizables.mostrarMensaje;

public class App extends Application {
    private Stage primaryStage;
    public ModelFactory modelFactory;

    /**
     * Metodo para inicializar el programa
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Billetera Virtual");
        openPrincipalView();
    }

    /**
     * Metodo para inicializar la vista principal
     */
    public void openPrincipalView() {
        try {
            mostrarMensaje("Contraseña admin",
                    "La contraseña del admin es: 2911", Alert.AlertType.INFORMATION);
            invocar();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("PrincipalView.fxml"));
            javafx.scene.layout.AnchorPane rootLayout = (javafx.scene.layout.AnchorPane) loader.load();
            PrincipalViewController principalViewController = loader.getController();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Metodo para invocar el ModelFactory
     */
    private void invocar() {
        modelFactory = ModelFactory.getInstance();
    }

    public static void main(String[] args) {
        launch();
    }
}