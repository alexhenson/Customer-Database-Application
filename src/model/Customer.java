package model;

/** This class is responsible for the functionality of the Customer class. */
public class Customer {
    private int customerId;
    private String customerName;
    private String address;
    private String division;
    private String postalCode;
    private String country;
    private String phone;

    /** This constructor initializes the fields from the seven parameters.
     *   @param customerId to set customerId
     *   @param customerName to set customerName
     *   @param address to set address
     *   @param division to set division
     *   @param postalCode to set postalCode
     *   @param country to set country
     *   @param phone to set phone
     */
    public Customer(int customerId, String customerName, String address, String division, String postalCode, String country, String phone) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.division = division;
        this.postalCode = postalCode;
        this.country = country;
        this.phone = phone;
    }

    /**
     * @return the customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode the postalCode to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the object as a String
     */
    @Override
    public String toString() { return "ID: " + customerId + ", Name: " + customerName; }
}
