package com.example.user.teenjobs;

import java.util.ArrayList;

/**
 * Created by USER on 13/12/2017.
 */

public class Employer {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String city;
    private String district;
    private String businessName;
    private String companyID;
    private ArrayList<String> requirements;


    //constructors:
    public Employer() {
    }
    public Employer(String firstName, String lastName, String email, String password, String phone, String city, String district, String businessName, String companyID, ArrayList<String> requirements) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.city = city;
        this.district = district;
        this.businessName = businessName;
        this.companyID = companyID;
        this.requirements = requirements;
    }

    //getters and setters:
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    public String getBusinessName() {
        return businessName;
    }
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
    public String getCompanyID() {
        return companyID;
    }
    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }
    public ArrayList<String> getRequirements() {
        return requirements;
    }
    public void setRequirements(ArrayList<String> requirements) {
        this.requirements = requirements;
    }


    @Override
    public String toString() {
        return "Employer{" +
                "businessName='" + businessName + '\'' +
                ", city='" + city + '\'' +
                ", companyID='" + companyID + '\'' +
                ", district='" + district + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", requirements='" + requirements + '\'' +
                '}';
    }
}