package dbAccess;

import dbConnection.JDBC;
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
        return clist;
    }

    public static void addCustomer(String customerName, String address, int division, String postalCode, String phone) {
        try {
            String sql = "INSERT INTO client_schedule.customers (Customer_ID, Customer_Name, Address, Division_ID, Postal_Code, Phone) \n" +
                    "VALUES (NULL, ?, ?, ?, ?, ?);";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, customerName);
            ps.setString(2, address);
            ps.setInt(3, division);
            ps.setString(4, postalCode);
            ps.setString(5, phone);

            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateCustomer(int customerId, String customerName, String address, int division, String postalCode, String phone) {
        try {
            String sql = "UPDATE client_schedule.customers \n" +
                    "SET Customer_Name = ?, Address = ?, Division_ID = ?, Postal_Code = ?, Phone = ?\n" +
                    "WHERE Customer_ID = ?;";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, customerName);
            ps.setString(2, address);
            ps.setInt(3, division);
            ps.setString(4, postalCode);
            ps.setString(5, phone);
            ps.setInt(6, customerId);


            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteCustomer(int customerId) {
        try {
            String sql = "DELETE FROM client_schedule.customers WHERE Customer_ID = ?;";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, customerId);
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

