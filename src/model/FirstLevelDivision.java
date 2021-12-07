package model;

/** This class is responsible for the functionality of the FirstLevelDivision class. */
public class FirstLevelDivision {
    private int divisionId;
    private String division;
    private int countryId;

    /** This constructor initializes the fields from the three parameters.
     *   @param divisionId to set divisionId
     *   @param division to set division
     *   @param countryId to set countryId
     */
    public FirstLevelDivision(int divisionId, String division, int countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return divisionId + ". " + division;
    }
}
