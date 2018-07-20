package models;

public class City {
    public String name;
    public String countryCode;
    public int population;

    @Override
    public String toString () {
        return "Country: " + countryCode + " || City: " + name + " || Population: " + population;
    }
}
