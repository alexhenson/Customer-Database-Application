package model;

public class FirstLevelDivision {
    private int divisionId;
    private String divisionName;
    private Country country;

    public FirstLevelDivision(int divisionId, String divisionName, Country country) {
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.country = country;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
