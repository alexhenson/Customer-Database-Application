package main;

import database.JDBC;
import dbAccess.DBCountries;
import dbAccess.DBUsers;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Country;
import model.User;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        primaryStage.setTitle("Login Form");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        JDBC.openConnection();
        ObservableList<User> userList = DBUsers.getAllUsers();
        for(User u: userList) {
            System.out.println("User ID: " + u.getUserId() + ", User Name: " + u.getUserName() + ", Password: " + u.getPassword());
        }
        launch(args);
    }
}
