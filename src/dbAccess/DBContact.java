package dbAccess;

import dbConnection.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;
import model.User;

import java.sql.*;

public class DBContacts {
    public static ObservableList<Contact> getAllContacts() {
        ObservableList<Contact> clist =FXCollections.observableArrayList();

        try {
            String sql = "SELECT Contact_ID, Contact_Name, Email FROM client_schedule.contacts;";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String email = rs.getString("Email");
                Contact c = new Contact(contactId, contactName, email);
                clist.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return clist;
    }
}

