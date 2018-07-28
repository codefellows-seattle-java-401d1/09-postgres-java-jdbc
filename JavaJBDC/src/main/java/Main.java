import db.WorldDB;

public class Main {
    public static void main(String[] args) {

        //final method that calls helper method to execute SQL query
        //for the list of all countries in the connected World Database
        WorldDB.getAllCountriesCommand();
        System.out.println();

        //final method that calls helper method to execute SQL query
        //for the list of all cities listed for each country
        // in the connected World Database
        WorldDB.getAllCitiesCommand();
        System.out.println();

        //final method that calls helper method to execute SQL query
        //for the list of all countries with a population less than 1,000
        // in the connected World Database
        WorldDB.getAllLowPopCommand();
        System.out.println();

        //final method that calls helper method to execute SQL query
        //for the list of all countries with a population greater than 20M
        // in the connected World Database
        WorldDB.getGetCountriesHighPopCommand();
        System.out.println();

        //final method that calls helper method to execute SQL query
        //for the list of all official languages of each country
        // in the connected World Database
        WorldDB.getOfficialLanguagesCommand();
        System.out.println();

    }
}

