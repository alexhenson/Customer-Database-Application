package main;

import dbConnection.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Locale currentLocale = Locale.getDefault();
        Locale locale = new Locale("en");

        ResourceBundle rb = ResourceBundle.getBundle("Nat", currentLocale);
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"), rb);

        //below is original
        primaryStage.setTitle(rb.getString("primaryStage.setTitle"));
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        JDBC.openConnection();
        launch(args);
    }
}
