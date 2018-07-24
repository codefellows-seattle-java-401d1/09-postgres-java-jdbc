package dbs;

import models.City;
import models.Country;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;

public class WorldDBTest {

    /*
    PER STEVE'S INSTRUCTIONS:
    Write a test for at least 3 of the required feature tasks below:
    - [X] One query that returns all cities or countries.
    - [X] One query that returns cities or countries constrained by one parameter.
    - [X] One query that returns results constrained by at least two parameters.
    - [X] One query that returns results based on an SQL join.
    
    (I decided to do all four since tests are fun. I will do more if I have the time, but focusing mostly on getting everything else done to full credit at this point.)
     */

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

            for (Country country : countries) {
            if (country.name.contains("Netherlands")) {
                isTrue = true;
            }
        }

        Boolean actual = isTrue;
        Boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test
    public void getCitiesInCountry() {
        String url = "jdbc:postgresql://localhost:5432/world";
        Properties props = new Properties();

        try {
            conn = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String GET_CITIES_COUNTRY = "SELECT * FROM city JOIN country ON city.countrycode = country.code WHERE country.code = ?";
        String countryCode = "USA";
        Boolean isTrue = false;

        List<City> cities = new ArrayList<>();

        try {
            PreparedStatement sql = this.conn.prepareStatement(GET_CITIES_COUNTRY);
            sql.setString(1, countryCode);

            ResultSet results = sql.executeQuery();
            while (results.next()) {
                City city = new City();
                city.name = results.getString("name");
                city.countryCode = results.getString("countrycode");
                city.population = results.getInt("population");
                cities.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (City city : cities) {
            if (city.name.contains("New York")) {
                isTrue = true;
            }

            Boolean actual = isTrue;
            Boolean expected = true;

            assertEquals(expected, actual);
        }
    }

    @Test
    public void getCountriesBetweenPopulation() {
        String url = "jdbc:postgresql://localhost:5432/world";
        Properties props = new Properties();

        try {
            conn = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String GET_COUNTRIES_BETWEEN = "SELECT * FROM country WHERE population > ? AND population < ?";
        List<Country> countries = new ArrayList<>();
        int lowBound = 20000;
        int highBound = 100000;
        Boolean isTrue = false;

        try {
            PreparedStatement sql = this.conn.prepareStatement(GET_COUNTRIES_BETWEEN);
            sql.setInt(1, lowBound);
            sql.setInt(2, highBound);

            ResultSet results = sql.executeQuery();

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

        for (Country country : countries) {
            if (country.name.contains("American Samoa")) {
                isTrue = true;
            }

            Boolean actual = isTrue;
            Boolean expected = true;

            assertEquals(expected, actual);
        }
    }

    @Test
    public void getSmallestCities() {
        String url = "jdbc:postgresql://localhost:5432/world";
        Properties props = new Properties();

        try {
            conn = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String TEN_SMALLEST_CITIES = "SELECT * FROM city ORDER BY population LIMIT ?";
        List<City> cities = new ArrayList<>();
        int numberOfCities = 10;
        Boolean isTrue = false;

        try {
            PreparedStatement sql = this.conn.prepareStatement(TEN_SMALLEST_CITIES);
            sql.setInt(1, numberOfCities);

            ResultSet results = sql.executeQuery();

            while (results.next()) {
                City city = new City();
                city.name = results.getString("name");
                city.countryCode = results.getString("countrycode");
                city.population = results.getInt("population");
                cities.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (City city : cities) {
            if (city.name.contains("Adamstown")) {
                isTrue = true;
            }

            Boolean actual = isTrue;
            Boolean expected = true;

            assertEquals(expected, actual);
        }
    }
}