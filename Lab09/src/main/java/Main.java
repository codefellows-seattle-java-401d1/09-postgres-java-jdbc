import Database.WorldDB;
import models.City;
import models.Country;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WorldDB db = new WorldDB();

        Scanner input = new Scanner(System.in);
        System.out.print("country code: ");
        String code = input.nextLine();

        for (City city : db.getCitiesInCountry(code)) {
            System.out.println(city);
        }
        System.out.println();

        System.out.println("All countries:");
        List<Country> countries = db.getAllCountries();
        for (Country country : countries) {
            System.out.println("  " + country);
        }
        System.out.println();

        System.out.println("Population 1,000-10,000");
        countries = db.getCountriesBetweenPopulation(1_000, 10_000);
        for (Country country : countries) {
            System.out.println("  " + country);
        }
        System.out.println();

        System.out.println("Abandoned countries:");
        countries = db.getCountriesBelowPopulation(1);
        for (Country country : countries) {
            System.out.println("  " + country);
        }
        System.out.println();

        System.out.println("Cities named after their country");
        List<City> cities = db.getCitiesNamedAfterCountries();
        for (City city : cities){
            System.out.println("   " + city + "in " + city);
        }

        System.out.println("Palindromic Cities");
        cities = db.getPallindromicCities();
        for (City city: cities){
            System.out.println("   " + city.name + "backwards is " + city.name);
        }
    }
}
