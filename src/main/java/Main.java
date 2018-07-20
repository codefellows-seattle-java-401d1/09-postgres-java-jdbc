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

// MAKING IT COOOOOOLLLL!


        // my cool method for steve's cool database

        //promps for number of population to filter by
        System.out.println("Countries with a population above " + number);

        List<Country> countries = db.getCountriesAbovePopulation(number);
        for (Country country : countries) {
            System.out.println("  " + country);
        }
        System.out.println();

        //scans for second question once list is generated:
        Scanner input2 = new Scanner(System.in);
        System.out.print("Type in one of those three character country codes above ");
        String country = input2.nextLine();
        System.out.print("The country you selected was " + country);

        // returns all cities from the country with their population
        for (City city : db.getCitiesInCountry(country)) {
            System.out.println(city);
        }
        System.out.println();

    }
}