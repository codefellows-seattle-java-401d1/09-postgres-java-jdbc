import com.google.gson.Gson;
import dbs.WorldDB;
import models.City;
import models.Country;

import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) {
        WorldDB db = new WorldDB();

        reportRequest();



//
//        System.out.println("Population 1,000-10,000");
//        countries = db.getCountriesBetweenPopulation(1_000, 10_000);
//        for (Country country : countries) {
//            System.out.println("  " + country);
//        }
//        System.out.println();
//
    }

    public static void allCountries () {
        WorldDB db = new WorldDB();

        System.out.println("All countries:");
        List<Country> countries = db.getAllCountries();
        for (Country country : countries) {
            System.out.println("  " + country);
        }
        System.out.println();
    }

    public static void allCities () {
        WorldDB db = new WorldDB();

        System.out.println("All cities:");
        List<City> cities = db.getAllCities();
        for (City city : cities) {
            System.out.println("  " + city);
        }
        System.out.println();
    }

    public static void citiesByCountryCode () {
        WorldDB db = new WorldDB();

        Scanner input = new Scanner(System.in);
        System.out.print("Country Code: ");
        String code = input.nextLine();

        for (City city : db.getCitiesInCountry(code)) {
            System.out.println(city);
        }
        System.out.println();
    }

    public static void largestCities () {
        WorldDB db = new WorldDB();

        System.out.println("Largest Cities:");
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the Number of cities you'd like to list from largest to smallest :");
        int code = parseInt(input.nextLine());

        for (City city : db.getLargestCities(code)) {
            System.out.println(city);
        }
        System.out.println();
    }

    public static void smallestCities () {
        WorldDB db = new WorldDB();

        System.out.println("Smallest Cities:");
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the Number of cities you'd like to list from smallest to largest :");
        int code = parseInt(input.nextLine());

        for (City city : db.getSmallestCities(code)) {
            System.out.println(city);
        }
        System.out.println();
    }

    public static void abandonedCountries () {
        WorldDB db = new WorldDB();

        System.out.println("Abandoned countries:");
        List<Country> countries = db.getAllCountries();
        countries = db.getCountriesBelowPopulation(1);
        for (Country country : countries) {
            System.out.println("  " + country);
        }
        System.out.println();

    }

    public static void countryLanguages () {
        WorldDB db = new WorldDB();

        for (Country country : db.getCountriesLanguages()) {
            System.out.println(country);
        }
        System.out.println();
    }


    private static String reportSelection() {
        Scanner reportInput = new Scanner(System.in);
        System.out.println("Select the report number:\n" +
                "1: All Countries\n" +
                "2: All Cities\n" +
                "3: Cities by Country Code\n" +
                "4: Largest Cities\n" +
                "5: Smallest Cites\n" +
                "6: Abandoned Countries\n" +
                "7: Country Languages");
        System.out.print("Report Number: ");
        String reportSelected = reportInput.nextLine();
        System.out.println();

        return reportSelected;
    }

    public static void reportRequest() {

        String report = reportSelection();

        Boolean whileRunning = true;

        while (whileRunning) {
            if (report.equals("1")) {
                allCountries();

            }else if (report.equals("2")) {
                allCities();

            } else if (report.equals("3")) {
                citiesByCountryCode();

            } else if (report.equals("4")) {
                largestCities();

            } else if (report.equals("5")) {
                smallestCities();

            } else if (report.equals("6")) {
                abandonedCountries();

            } else if (report.equals("7")) {
                countryLanguages();

            } else {
                System.out.println("That report does not exist. Please try again.");
                reportRequest();
            }
            whileRunning = false;
        }
    }

}