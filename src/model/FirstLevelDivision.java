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

    /**
     * @return the divisionId
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * @param divisionId the divisionId to set
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * @return the division
     */
    public String getDivision() {
        return division;
    }

    /**
     * @param division the division to set
     */
    public void setDivision(String division) {
        this.division = division;
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
     * @return the object as a String
     */
    @Override
    public String toString() {
        return divisionId + ". " + division;
    }
}
