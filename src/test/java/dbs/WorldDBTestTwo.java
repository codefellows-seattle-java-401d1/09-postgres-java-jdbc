package dbs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class WorldDBTestTwo {

    @BeforeEach
    void setUp() {
        Connection conn;

        String url = "jdbc:postgresql://localhost:5432/world";
        Properties props = new Properties();

        try {
            conn = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    @Test
    void getAllCountries() {
    }

    @Test
    void getCountriesBelowPopulation() {
    }

    @Test
    void getAllCities() {
    }

    @Test
    void getCitiesInCountry() {
    }

    @Test
    void getCountriesBetweenPopulation() {
    }

    @Test
    void getLargestCities() {
    }

    @Test
    void getSmallestCities() {
    }

    @Test
    void getCountriesLanguages() {
    }
}