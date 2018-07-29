package dbInteraction;

import models.City;
import models.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorldDBInterface {
    private static final String GET_ALL_COUNTRIES = "SELECT * FROM country";
    private static final String GET_COUNTRIES_UNDER_LIMIT =
            "SELECT * FROM country WHERE population < ?";
    private static final String GET_COUNTRIES_BETWEEN =
            "SELECT * FROM country WHERE population > ? AND population < ?";
    private static final String GET_CITIES_BETWEEN =
            "SELECT * FROM city WHERE population > ? AND population < ?";
    private static final String GET_CITIES_COUNTRY =
            "SELECT * FROM city " +
                    "JOIN country ON city.countrycode = country.code " +
                    "WHERE country.code = ?";

    String host = "dbsamples-0.1/world/world.sql";
    String user = "postgres";
    String pass = "postgres";

    private Connection conn = DriverManager.getConnection(host,user,pass);

    public List<Country> getAllCountries(){
        List<Country> countries = new ArrayList<>();

        try {
            Statement sql = this.conn.createStatement();
            ResultSet results = sql.executeQuery(GET_ALL_COUNTRIES);

            while(results.next()) {
                Country country = new Country();
                country.name = results.getString("name");
                country.countryCode = results.getString("code");
                country.population = results.getInt("population");
                countries.add(country);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return countries;
    }

    public List<City> getAllCities() {
        List<City> cities = new ArrayList<>();
        try {
            Statement sql = this.conn.createStatement();
            ResultSet results = sql.executeQuery(GET_ALL_COUNTRIES);

            while(results.next()){
                City newCity = new City();
                newCity.name = results.getString("name");
                newCity.countryCode = results.getString("code");
                newCity.population = results.getInt("population");
                cities.add(newCity);
            }
            return cities;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return cities;
    }

    public int getAdjustedPopulation(double change, String cityName){
        //assumes that change is like 1.03 for a 3 percent increase.
        int population = 0;

        try{
            PreparedStatement sql = this.conn.prepareStatement("SELECT population FROM city WHERE name ="+cityName);
            ResultSet results = sql.executeQuery();
            population = results.getInt("population");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return (int)((double) population * change);
    }

    public List<City> getCitiesIncountry(String countryCode){
    List<City> cities = new ArrayList<>();

        try {
            PreparedStatement sql = this.conn.prepareStatement(GET_CITIES_COUNTRY);
            sql.setString(1, countryCode);

            ResultSet results = sql.executeQuery();
            while(results.next()){
                City city = new City();
                city.name = results.getString("Name");
                city.countryCode = results.getString("countrycode");
                city.population = results.getInt("population");
                cities.add(city);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cities;
    }

    public List<Country> getCountriesBetweenPopulation(int lowBound, int highBound){
        List<Country> countries = new ArrayList<>();

        try {
            PreparedStatement sql = this.conn.prepareStatement(GET_COUNTRIES_BETWEEN);
            sql.setInt(1,lowBound);
            sql.setInt(2,highBound);

            ResultSet results = sql.executeQuery();
            while(results.next()) {
                Country country = new Country();
                country.name = results.getString("name");
                country.population = results.getInt("population");
                countries.add(country);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return countries;
    }

    public List<City> getCitiesBetweenPopulation(int lowBound, int highBound){
        List<City> Cities = new ArrayList<>();

        try {
            PreparedStatement sql = this.conn.prepareStatement(GET_CITIES_BETWEEN);
            sql.setInt(1,lowBound);
            sql.setInt(2,highBound);

            ResultSet results = sql.executeQuery();
            while(results.next()) {
                City newCity = new City();
                newCity.name = results.getString("name");
                newCity.population = results.getInt("population");
                Cities.add(newCity);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return Cities;
    }

}
