package model;

/** This class is responsible for the functionality of the Country class. */
public class Country {
    private int countryId;
    private String countryName;

    /** This constructor initializes the fields from the two parameters.
     *   @param countryId to set countryId
     *   @param countryName to set countryName
     */
    public Country(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    /**
     * @return the countryId
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * @param countryId the countryId to set
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * @return the countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * @param countryName the countryName to set
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * @return the object as a String
     */
    @Override
    public String toString() {
        return countryId + ". " + countryName;
    }
}
