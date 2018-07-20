package models;

public class Languages {
    public String countryCode;
    public String language;
    public String isOfficial;
    public String percentage;

        public String toString(){
            return language + " is an official language of " + countryCode + ". It is spoken by " + percentage + "% of the population";
    }
}
