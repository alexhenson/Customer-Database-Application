package main;

import dbConnection.JDBC;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Appointment;
import tools.TimeHelper;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/** This class is the starting point for the entire customer appointment database program. */
public class Main extends Application {
    /** This method loads the initial stage and "Login Form", the first scene for the customer
     *  appointment database program. The program is ready to present the Login screen in both
     *  English and French.
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Locale currentLocale = Locale.getDefault();
        //Locale locale = Locale.FRENCH;

        ResourceBundle rb = ResourceBundle.getBundle("Nat", currentLocale);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Login.fxml")), rb);

        primaryStage.setTitle(rb.getString("primaryStage.setTitle"));
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    /** This method is the main method that will instantiate all the initial sample data for the typeList.
     *  The Javadoc folder is located in C:\Users\LabUser\IdeaProjects\C195_Project_V2\index.html
     *  @param args an array of String arguments
     */
    public static void main(String[] args) {
        JDBC.openConnection();

        ObservableList<String> typeList = FXCollections.observableArrayList();
        typeList.add("Planning Session");
        typeList.add("De-Briefing");
        typeList.add("Financial Advisory");
        typeList.add("Brainstorming Session");
        typeList.add("Career Planning");
        Appointment.setTypeList(typeList);

        launch(args);
    }
}
