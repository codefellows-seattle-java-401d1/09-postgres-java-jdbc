package models;

public class Languages {
    public String countryCode;
    public String language;
    public Boolean isOfficial;
    public Float percentage;

    public String toString() {
        return countryCode + "   " + "Language: " + language + "\n" +
                "(Official Language?  " + isOfficial + ")" + "\n" +
                "Percentage of speakers: " + percentage + "\n";
    }
}
