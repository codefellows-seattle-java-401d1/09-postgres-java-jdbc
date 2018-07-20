package models;

public class Language {
    public String countryCode;
    public String language;

    @Override
    public String toString() {
        return countryCode + " (" + language + ")";
    }
}
