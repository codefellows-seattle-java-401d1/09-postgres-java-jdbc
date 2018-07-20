package models;

public class Languages {
    public String countrycode;
    public String language;
    public Boolean isofficial;
    public Float percentage;

    public String toString() {
        return countrycode + " " + language + " " + "(Official: " + isofficial + ")" + "Percentage of speakers: " + percentage;
    }
}
