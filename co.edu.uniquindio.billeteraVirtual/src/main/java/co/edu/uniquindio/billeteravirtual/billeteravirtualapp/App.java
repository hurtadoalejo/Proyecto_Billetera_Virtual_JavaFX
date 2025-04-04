package co.edu.uniquindio.billeteravirtual.billeteravirtualapp;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.factory.ModelFactory;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.viewController.PrincipalViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class App extends Application {
    private Stage primaryStage;
    public ModelFactory modelFactory;

    /**
     * Metodo para inicializar la interfaz principal
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

    private void invocar() {
        modelFactory = ModelFactory.getInstance();
    }

    public static void main(String[] args) {
        launch();
    }
}