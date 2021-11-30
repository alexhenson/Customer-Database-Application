package dbAccess;

import dbConnection.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.time.LocalDateTime;

public class DBAppointments {
    public static @NotNull
    ObservableList<Appointment> getAllAppointments() {
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
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");

                Appointment a = new Appointment(apptId, title, description, location, contact, type, start, end, customerId, userId);
                alist.add(a);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return alist;
    }

    public static void addAppointment(String title,String description, String location, String type, Timestamp start, Timestamp end, int customerId, int userId, int contactId) {
        try {
            String sql = "INSERT INTO client_schedule.appointments (Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) \n" +
                    "VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setTimestamp(5, start);
            ps.setTimestamp(6, end);
            ps.setInt(7, customerId);
            ps.setInt(8, userId);
            ps.setInt(9, contactId);

            ps.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateAppointment(int appointmentId, String title,String description, String location, String type, Timestamp start, Timestamp end, int customerId, int userId, int contactId) {
        try {
            String sql = "UPDATE client_schedule.appointments\n" +
                    "SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, \n" +
                    "Customer_ID = ?, User_ID = ?, Contact_ID = ?\n" +
                    "WHERE Appointment_ID = ?;";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setTimestamp(5, start);
            ps.setTimestamp(6, end);
            ps.setInt(7, customerId);
            ps.setInt(8, userId);
            ps.setInt(9, contactId);
            ps.setInt(10, appointmentId);

            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteAppointment(int appointmentId) {
        try {
            String sql = "DELETE FROM client_schedule.appointments WHERE Appointment_ID = ?;";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, appointmentId);
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}


