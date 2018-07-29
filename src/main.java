import java.util.List;
import java.util.Scanner;
import dbInteraction.WorldDBInterface;
import models.City;
import models.Country;

public class main {
    public static void main(String[] args) {
        WorldDBInterface db = new WorldDBInterface();
        Scanner inputTake = new Scanner(System.in);
        System.out.println("1. Search World Database\n2. View All");
        int Selection=-1;
        int Selection2=-1;
        try {
            Selection = Integer.parseInt(inputTake.nextLine());
        }catch(NumberFormatException error){
            System.out.println(error);
            System.out.println("Error from improper input.");
        }
        try {
            System.out.println("1. Cities\n2. Countries");
            Selection2 = Integer.parseInt(inputTake.nextLine());
        }catch(NumberFormatException error){
            System.out.println(error);
            System.out.println("Error from improper input.");
        }
        //Search
        if(Selection == 1){
            //Cities
            if(Selection2==1){
                System.out.println("1. Search by country\n2. Search by population");
                try {
                    int SearchModeSelection = Integer.parseInt(inputTake.nextLine());
                    if(SearchModeSelection==1){
                        System.out.println("Which country code would you like to search for?");
                        String selectedCountry = inputTake.nextLine();
                        List <City> Results = db.getCitiesIncountry(selectedCountry);
                        for(int i = 0;i<Results.size();i++){
                            City result = Results.get(i);
                            System.out.println(result.name);
                            System.out.println(result.population);
                            System.out.println(result.countryCode+"\n");
                        }
                    }
                    if(SearchModeSelection==2){
                        System.out.println("Choose the lower bound.");
                        int selectedPopulation = Integer.parseInt(inputTake.nextLine());
                        System.out.println("Choose the upper bound.");
                        int selectedPopulation1 = Integer.parseInt(inputTake.nextLine());
                        List<City> Results = db.getCitiesBetweenPopulation(selectedPopulation,selectedPopulation1);
                        for(int i = 0;i<Results.size();i++){
                            City result = Results.get(i);
                            System.out.println(result.name);
                            System.out.println(result.population);
                            System.out.println(result.countryCode+"\n");
                        }
                    }
                }catch(NumberFormatException error){
                    System.out.println(error);
                    System.out.println("Error from improper input.");
                }

            }
            //Countries
            if(Selection2==2){
                System.out.println("Choose the lower bound.");
                int selectedPopulation = Integer.parseInt(inputTake.nextLine());
                System.out.println("Choose the upper bound.");
                int selectedPopulation1 = Integer.parseInt(inputTake.nextLine());
                List<Country> Results = db.getCountriesBetweenPopulation(selectedPopulation,selectedPopulation1);
                for(int i = 0;i<Results.size();i++){
                    Country result = Results.get(i);
                    System.out.println(result.name);
                    System.out.println(result.population);
                    System.out.println(result.countryCode+"\n");
                }
            }
        }
        //All
        if(Selection == 2){
            if(Selection2==1){
                List<City> Results = db.getAllCities();
                for(int i = 0;i<Results.size();i++){
                    City result = Results.get(i);
                    System.out.println(result.name);
                    System.out.println(result.population);
                    System.out.println(result.countryCode+"\n");
                }
            }
            if(Selection2==2){
                List<Country> results = db.getAllCountries();
            }
        }
        if(Selection != 1 && Selection != 2){
            System.out.println("Invalid number selected.");
        }

    }
}
