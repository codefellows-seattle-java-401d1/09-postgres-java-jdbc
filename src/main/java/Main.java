import dbs.WorldDB;
import models.City;
import models.Country;
import models.Language;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Postgres driver not configured correctly.");
        }

        WorldDB db = new WorldDB();

        Scanner input = new Scanner(System.in);
        System.out.println("Enter a country code to get it's cities sorted by highest population:");
        String code = input.nextLine();


        int count = 1;

        for (City city : db.getCitiesInCountry(code)) {
            System.out.print(count + ": ");
            System.out.println(city);
            System.out.println();
            count++;
        }

        Scanner input2 = new Scanner(System.in);
        System.out.println("Enter a language to see which countries use it:");
        String lang = input2.nextLine();

        count = 1;

        for (Language language : db.getCountriesByLanguage(lang)) {
            System.out.println(count + ": ");
            System.out.println(language);
            System.out.println();
            count++;
        }

    }
}
