package db;

import models.models.Country;
import models.models.Language;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;

//ALL CREDIT TO AMY COHEN for showing me structure of tests in her repo.
//Would have been lost without the reference!
// PER STEVE'S INSTRUCTIONS:
//         Write a test for at least 3 of the required feature tasks below:
//         - [X] One query that returns all cities or countries.
//         - [X] One query that returns cities or countries constrained by one parameter.
//         - [ ] One query that returns results constrained by at least two parameters.
//         - [X] One query that returns results based on an SQL join.

public class WorldDBTest {
    private Connection conn;
    private String url = "jdbc:postgresql://localhost:5432/world";
    private Properties props = new Properties();

    @Test
    public void getAllCountriesTest() {
        try {
            this.conn = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DB error.");
        }

        String GET_ALL_COUNTRIES = "SELECT * FROM country";
        List<Country> countriesAll = new ArrayList<>();

        boolean isTrue= false;
        try {
            Statement sql = this.conn.createStatement();
            ResultSet results = sql.executeQuery(GET_ALL_COUNTRIES);
            while (results.next()) {
                Country country = new Country();
                country.name = results.getString("name");
                country.countryCode = results.getString("code");
                country.population = results.getInt("population");
                countriesAll.add(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Country country : countriesAll) {
            if (country.name.contains("Tonga")) {
                isTrue = true;
            }
        }
        assertEquals(true, isTrue);

    }

    @Test
    public void getCountriesBelowPopulationTest() {
        try {
            this.conn = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DB error.");
        }

        String GET_COUNTRIES_BELOW_POPULATION =
                "SELECT * FROM country WHERE population <?";
        List<Country> countriesPop = new ArrayList<>();
        int population = 1000;

        boolean isTrue = false;
        try {
            PreparedStatement sql = this.conn.prepareStatement(GET_COUNTRIES_BELOW_POPULATION);
            sql.setInt(1, population);

            ResultSet results = sql.executeQuery();
            while (results.next()) {
                Country country = new Country();
                country.name = results.getString("name");
                country.countryCode = results.getString("code");
                country.population = results.getInt("population");
                countriesPop.add(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Country country : countriesPop) {
            if (country.name.contains("Pitcairn")) {
                isTrue = true;
            }
        }

        assertEquals(true, isTrue);

    }

    @Test
    public void getCountriesOfficialLanguage() {
        try {
            this.conn = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DB error.");
        }
        String GET_LANGUAGES = "SELECT * FROM countrylanguage JOIN" +
                " country ON countrylanguage.countrycode = country.code " +
                "WHERE countrylanguage.isofficial is TRUE";
        List<Language> languages = new ArrayList<>();
        boolean isTrue = false;
        try {
            PreparedStatement sql = this.conn.prepareStatement(GET_LANGUAGES);

            ResultSet results = sql.executeQuery();
            while (results.next()) {
                Language language = new Language();
                language.countryCode = results.getString("countrycode");
                language.language = results.getString("language");
                language.isofficial = results.getBoolean("isofficial");
                languages.add(language);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Language language: languages) {
            if(language.language.contains("Mandarin")){
                isTrue = true;
            }
        }
        assertEquals(true, isTrue);
    }
}

