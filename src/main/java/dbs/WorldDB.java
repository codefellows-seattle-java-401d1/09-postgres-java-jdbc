package dbs;

import models.City;
import models.Country;
import models.Language;

import java.sql.*;
import java.util.*;

public class WorldDB {
    private static final String GET_ALL_COUNTRIES = "SELECT * FROM country";
    private static final String GET_CITIES_BY_COUNTRY =
            "SELECT * FROM city " +
            "JOIN country ON city.countrycode = country.code " +
            "WHERE country.code = ?";
    private static final String GET_COUNTRIES_BY_LANGUAGE =
            "SELECT * FROM countrylanguage WHERE countrylanguage.language = ?";

    private Connection conn;

    public WorldDB () {
        String url = "jdbc:postgresql://localhost:5432/world";
        Properties props = new Properties();

        try {
            this.conn = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Country> getAllCountries () {
        List<Country> countries = new ArrayList<>();

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

        return countries;
    }

    public List<City> getCitiesInCountry(String countryCode) {
        List<City> cities = new ArrayList<>();

        try {
            PreparedStatement sql = this.conn.prepareStatement(GET_CITIES_BY_COUNTRY);
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

        return cities;
    }

    public List<Language> getCountriesByLanguage (String lang) {
        List<Language> languages = new ArrayList<>();

        try {
            PreparedStatement sql = this.conn.prepareStatement(GET_COUNTRIES_BY_LANGUAGE);
            sql.setString(1, lang);

            ResultSet results = sql.executeQuery();
            while (results.next()) {
                Language language = new Language();
                language.countryCode = results.getString("countrycode");
                language.language = results.getString("language");
                language.percentage = results.getString("percentage");
                languages.add(language);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return languages;
    }
}
