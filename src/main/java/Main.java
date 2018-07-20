import com.google.gson.Gson;
import dbs.WorldDB;
import models.City;
import models.Country;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WorldDB db = new WorldDB();

        Scanner input = new Scanner(System.in);
        System.out.print("Countries that have a population greater than: ");
        String number = input.nextLine();

//        for (City city : db.getCitiesInCountry(code)) {
//            System.out.println(city);
//        }
//        System.out.println();
//
//        System.out.println("All countries:");
//        List<Country> countries = db.getAllCountries();
//        for (Country country : countries) {
//            System.out.println("  " + country);
//        }
//        System.out.println();
//
//        System.out.println("Population 1,000-10,000");
//        countries = db.getCountriesBetweenPopulation(1_000, 10_000);
//        for (Country country : countries) {
//            System.out.println("  " + country);
//        }
//        System.out.println();
//
//        System.out.println("Abandoned countries:");
//        countries = db.getCountriesBelowPopulation(1);
//        for (Country country : countries) {
//            System.out.println("  " + country);
//        }
//        System.out.println();


// MAKING IT COOOOOOLLLL!


        // my cool method for steve's cool database
        System.out.println("Countries with a population above");

        List<Country> countries = db.getCountriesAbovePopulation(number);
        for (Country country : countries) {
            System.out.println("  " + country);
        }
        System.out.println();
    }
}