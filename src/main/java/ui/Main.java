package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.net.URL;

public class Main extends Application {

    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("before Launch");
        launch(args);
        log.info("After Launch");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL guiUrl = getClass().getClassLoader().getResource("sample.fxml");
        if (guiUrl == null) {
            //Error window
            return;
        }
        Parent root = FXMLLoader.load(guiUrl);
        primaryStage.setTitle("Definite Integral Calulator");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
