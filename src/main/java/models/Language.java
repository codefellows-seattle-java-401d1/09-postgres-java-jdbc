package models;

public class Language {
    public String language;
    public String countryCode;
    public String percentage;

    @Override
    public String toString () {
        return "Country: " + countryCode + " || Language: " + language + " || Percentage: " + percentage;
    }
}
