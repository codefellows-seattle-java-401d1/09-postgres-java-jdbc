package dbs;

import models.Country;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;

public class WorldDBTest {


    private Connection conn;

    @Test
    public void getAllCountries() {

        String url = "jdbc:postgresql://localhost:5432/world";
        Properties props = new Properties();

        try {
            conn = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String GET_ALL_COUNTRIES = "SELECT * FROM country";
        List<Country> countries = new ArrayList<>();

        Boolean isTrue = false;

        try {
            Statement sql = this.conn.createStatement();
            ResultSet results = sql.executeQuery(GET_ALL_COUNTRIES);

            while (results.next()) {
                Country country = new Country();
                country.name = results.getString("name");
                country.countryCode = results.getString("code");
                country.population = results.getInt("population");
                countries.add(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

//            for (Country country : countries) {
            for (int i = 0; i < countries.size(); i++)
            if (countries[i] == 1) {
                isTrue = true;
            } else {
                isTrue = false;
            }
        }

        Boolean actual = isTrue;
        Boolean expected = true;

        assertEquals(expected, actual);
    }

//    @Test
//    public void getCountriesBelowPopulation() {
//    }
//
//    @Test
//    public void getAllCities() {
//    }
//
//    @Test
//    public void getCitiesInCountry() {
//    }
//
//    @Test
//    public void getCountriesBetweenPopulation() {
//    }
//
//    @Test
//    public void getLargestCities() {
//    }
//
//    @Test
//    public void getSmallestCities() {
//    }
//
//    @Test
//    public void getCountriesLanguages() {
//    }
}