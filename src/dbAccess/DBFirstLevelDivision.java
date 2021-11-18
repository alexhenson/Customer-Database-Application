package dbAccess;

import database.JDBC;
import model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FirstLevelDivision;

import java.sql.*;

public class DBFirstLevelDivision {
    public static ObservableList<FirstLevelDivision> getAllDivisions() {
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

