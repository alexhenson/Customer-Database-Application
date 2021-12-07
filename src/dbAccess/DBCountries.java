package dbAccess;

import dbConnection.JDBC;
import model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/** This class is responsible for the SQL functionality for Appointment objects. */
public class DBCountries {
    /** This method uses SQL specific methods to access a database and create an ObservableList of Country objects.
     *  @return ObservableList of Country objects
     */
    public static ObservableList<Country> getAllCountries() {
        ObservableList<Country> clist =FXCollections.observableArrayList();

        try {
            String sql = "SELECT Country_ID, Country FROM client_schedule.countries;";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int countryId = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                Country c = new Country(countryId, countryName);
                clist.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return clist;
    }
}
