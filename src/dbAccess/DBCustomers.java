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
            String sql = "SELECT Customer_ID, Customer_Name, Address, Division, Postal_Code, Country, Phone \n" +
                         "FROM client_schedule.customers cu \n" +
                         "JOIN client_schedule.first_level_divisions f ON cu.Division_ID = f.Division_ID\n" +
                         "JOIN client_schedule.countries co ON co.Country_ID = f.Country_ID;";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String division = rs.getString("Division");
                String postalCode = rs.getString("Postal_Code");
                String country = rs.getString("Country");
                String phone = rs.getString("Phone");

                Customer c = new Customer(customerId, customerName, address, division, postalCode, country, phone);
                clist.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.print(clist);
        return clist;
    }
}

