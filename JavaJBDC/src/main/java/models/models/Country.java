package models;

public class Country {
    public String name;
    public int population;
    public String countryCode;

    public String toString(){
        return countryCode + " " + name + " (" + population +")";
    }
}
