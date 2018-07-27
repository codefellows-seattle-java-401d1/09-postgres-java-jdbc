import dbs.WorldDB;
import models.Country;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        WorldDB db = new WorldDB();
        System.out.println("All countries in 1998 database");
        List<Country> countries = db.getAllCountries();
        for (Country country : countries) {
            System.out.println("  " + country);
        }
        System.out.println();

        System.out.println("Abandoned countries with populations less than 10: ");
        countries = db.getCountriesBelowPopulation(10);
        for (Country country : countries) {
            System.out.println("Empty countries: " + country);
        }
        System.out.println();

        System.out.println("Countries with populations greater than 20 million: ");
        countries = db.getCountriesGreaterThanPopulation(20000000);
        for (Country country : countries) {
            System.out.println("Empty countries: " + country);
        }
    }
}

