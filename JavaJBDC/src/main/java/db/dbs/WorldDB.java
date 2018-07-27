package db;

import models.City;
import models.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class WorldDB {
    private Connection conn;
    private static final String GET_ALL_COUNTRIES = "SELECT * FROM country";
    private static final String GET_ALL_CITIES = "SELECT * FROM city";
    private static final String GET_COUNTRIES_BELOW_POPULATION =
            "SELECT * FROM country WHERE population <?";
    private static final String GET_COUNTRIES_ABOVE_POPULATION =
            "SELECT * FROM country WHERE population >?";


    public WorldDB() {
        String url = "jdbc:postgresql://localhost:5432/world";
        Properties props = new Properties();

        //good notes for future reference in other labs
        // use these properties if you need to provide a username or password.
        //props.setProperty("user","fred");
        //props.setProperty("password","secret");
        //props.setProperty("ssl","true");

        try {
            this.conn = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DB error.");
        }
    }

    public List<Country> getAllCountries() {
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

    public List<City> getAllCities(){
        List<City> cities = new ArrayList<>();
        try {
            Statement sql = this.conn.createStatement();
            ResultSet results = sql.executeQuery(GET_ALL_CITIES);
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

//    public List<City> getCitiesInCountry(){
//        List<City> citiesInCountry = new ArrayList<>();
//        try {
//            Statement sql = this.conn.createStatement();
//            ResultSet results = sql.executeQuery(GET_CITIES_IN_COUNTRIES);
//            while (results.next()) {
//                City city = new City();
//                city.name = results.getString("name");
//                city.countryCode = results.getString("countrycode");
//                city.population = results.getInt("population");
//                citiesInCountry.add(city);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//
//        }
//        return citiesInCountry;
//    }

    public List<Country> getCountriesBelowPopulation(int population) {
        List<Country> countries = new ArrayList<>();
        try {
            PreparedStatement sql = this.conn.prepareStatement( GET_COUNTRIES_BELOW_POPULATION);
            sql.setInt(1, population);

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
        return countries;
    }

    public List<Country> getCountriesGreaterThanPopulation(int population) {
        List<Country> countries = new ArrayList<>();
        try {
            PreparedStatement sql = this.conn.prepareStatement( GET_COUNTRIES_ABOVE_POPULATION);
            sql.setInt(1, population);

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
        return countries;
    }

}


//        ResultSet results = conn.createStatement().executeQuery("SELECT * FROM countries");
//        while (results.next()) {
//        int id = results.getInt("id");
//        String name = results.getString("name");
//        Date createdAt = results.getDate("createdAt");
//        Date updatedAt = results.getDate("updatedAt");
//
//        System.out.println("id: " + id + " artist: " + name);
