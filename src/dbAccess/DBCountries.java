package dbAccess;

import database.JDBC;
import javafx.beans.Observable;
import model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DBCountries {
    public static ObservableList<Country> getAllCountries() {
        ObservableList<Country> clist =FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM countries";
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
