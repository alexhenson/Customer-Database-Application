package dbAccess;

import database.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.*;

public class DBAppointments {
    public static ObservableList<Appointment> getAllAppointments() {
        ObservableList<Appointment> alist =FXCollections.observableArrayList();

        try {
            String sql = "SELECT Appointment_ID, Title, Description, Location, Contact_Name, Type, Start, End, Customer_ID, User_ID \n" +
                         "FROM client_schedule.appointments a \n" +
                         "JOIN client_schedule.contacts c ON a.Contact_ID = c.Contact_ID;";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int apptId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String contact = rs.getString("Contact_Name");
                String type = rs.getString("Type");
                Timestamp start = rs.getTimestamp("Start");
                Timestamp end = rs.getTimestamp("End");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");

                Appointment a = new Appointment(apptId, title, description, location, contact, type, start, end.toLocalDateTime(), customerId, userId);
                alist.add(a);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return alist;
    }
}


