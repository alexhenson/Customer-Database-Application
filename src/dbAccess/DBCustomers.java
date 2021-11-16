package dbAccess;

import database.JDBC;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.*;

public class DBCustomers {
    public static ObservableList<Customer> getAllCustomers() {
        ObservableList<Customer> clist =FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM customers";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                int division = rs.getInt("Division_ID");

                Customer c = new Customer(customerId, customerName, address, postalCode, phone, division);
                clist.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return clist;
    }
}

