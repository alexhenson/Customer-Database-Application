package dbAccess;

import dbConnection.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FirstLevelDivision;

import java.sql.*;

/** This class is responsible for the SQL functionality for Appointment objects. */
public class DBDivisions {
    /** This method uses SQL specific methods to access a database and create an ObservableList of Division objects.
     *  @return ObservableList of Division objects
     */
    public static ObservableList<FirstLevelDivision> getAllDivisions() {
        ObservableList<FirstLevelDivision> flist =FXCollections.observableArrayList();

        try {
            String sql = "SELECT Division_ID, Division, Country_ID FROM client_schedule.first_level_divisions;";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int divisionId = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                int countryId = rs.getInt("Country_ID");
                FirstLevelDivision f = new FirstLevelDivision(divisionId, division, countryId);
                flist.add(f);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return flist;
    }
}

