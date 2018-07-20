import dbs.WorldDB;
import models.Languages;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WorldDB db = new WorldDB();


        System.out.println("Check the offical language of any country!");
        System.out.print("country code: ");
        Scanner input = new Scanner(System.in);
        String code = input.nextLine();
        code = code.toUpperCase();


        for(Languages languages : db.getOfficailLanguage(code)) {
            System.out.println("Results:");
            System.out.println(languages);

            
        }

    }
}
