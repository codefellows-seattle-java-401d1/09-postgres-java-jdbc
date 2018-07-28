package db;

import models.models.City;
import models.models.Country;
import models.models.Language;

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

    //help from StackOverflow on how to return a true result from a SQl query
    //https://stackoverflow.com/questions/26141312/sql-return-row-if-boolean-is-true
    //thanks to W3Schools for refresher on SEQUEL joins
    //https://www.w3schools.com/sql/sql_join.asp
    //FOUND FIX! Looked at Amy Cohen's query to make my own
    private static final String GET_LANGUAGES = "SELECT * FROM countrylanguage JOIN" +
            " country ON countrylanguage.countrycode = country.code " +
            "WHERE countrylanguage.isofficial is TRUE";


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
        List<Country> countriesAll = new ArrayList<>();
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
        return countriesAll;
    }

    public static String getAllCountriesCommand() {
        WorldDB db = new WorldDB();
        System.out.println("All countries in 1998 database");
        List<Country> countriesAll = db.getAllCountries();
        for (Country country : countriesAll) {
            System.out.println("  " + country.name);
        }
        return "Thanks for your query.";
    }

    public List<City> getAllCities() {
        List<City> citiesAll = new ArrayList<>();
        try {
            Statement sql = this.conn.createStatement();
            ResultSet results = sql.executeQuery(GET_ALL_CITIES);
            while (results.next()) {
                City city = new City();
                city.name = results.getString("name");
                city.countryCode = results.getString("countrycode");
                city.population = results.getInt("population");
                citiesAll.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return citiesAll;
    }

    public static String getAllCitiesCommand() {
        WorldDB db = new WorldDB();
        System.out.println("Largest cities by population by country");
        List<City> citiesAll = db.getAllCities();
        for (City city : citiesAll) {
            System.out.println("  " + city.countryCode + " with city: " + city.name);
        }
        return "Thanks for your query.";
    }

    public List<Country> getCountriesBelowPopulation(int population) {
        List<Country> countriesPop = new ArrayList<>();
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
        return countriesPop;
    }

    public static String getAllLowPopCommand() {
        WorldDB db = new WorldDB();
        System.out.println("Abandoned countries with populations less than 1,000: ");
        List<Country> countriesPop = db.getCountriesBelowPopulation(1000);
        for (Country country : countriesPop) {
            System.out.println(" " + country.name + " population: " + country.population);
        }
        return "Thanks for your query.";
    }

    public List<Country> getCountriesGreaterThanPopulation(int population) {
        List<Country> countriesGigaPop = new ArrayList<>();
        try {
            PreparedStatement sql = this.conn.prepareStatement(GET_COUNTRIES_ABOVE_POPULATION);
            sql.setInt(1, population);

            ResultSet results = sql.executeQuery();
            while (results.next()) {
                Country countryGigaPop = new Country();
                countryGigaPop.name = results.getString("name");
                countryGigaPop.countryCode = results.getString("code");
                countryGigaPop.population = results.getInt("population");
                countriesGigaPop.add(countryGigaPop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countriesGigaPop;
    }

    public static String getGetCountriesHighPopCommand() {
        WorldDB db = new WorldDB();
        System.out.println("Countries with populations greater than 20 million: ");
        List<Country> countriesGigaPop = db.getCountriesGreaterThanPopulation(20000000);
        for (Country country : countriesGigaPop) {
            System.out.println(" " + country.name + " population: " + country.population);
        }
        return "Thanks for your query.";
    }

     public List<Language> getCountriesOfficialLanguage() {
        List<Language> languages = new ArrayList<>();
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
        return languages;
    }
    public static String getOfficialLanguagesCommand(){
        WorldDB db = new WorldDB();

        System.out.println("Official Languages of all countries in database: ");

        List<Language> languages = db.getCountriesOfficialLanguage();

        for (Language language: languages) {
            System.out.println(" " + language.countryCode + "'s official language is: " + language.language);
        }

        System.out.println();
        return "Thank you for your query.";
    }
}


