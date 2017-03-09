package edu.sunysuffolk.cst246;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Main Class exists to allow us to launch the JavaFX GUI.
 *
 * @author Rudy Gamberini
 * @version February 14th, 2017
 */
public class Main extends Application {

    /**
     * The start method is automatically called by JavaFX
     * The main entry point for all JavaFX applications.
     *
     * @param primaryStage the primary stage for this application, onto which the application scene can be set. The primary stage will be embedded in the browser if the application was launched as an applet. Applications may create other stages, if needed, but they will not be primary stages and will not be embedded in the browser.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Gamberini Doscher Unsorted Optimized Array");
        Scene scene = new Scene(root, 655, 498);
        scene.getStylesheets().add(getClass().getResource("material-fx.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Begins the JavaFX launch process.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
