package com.example.user.teenjobs;


import org.joda.time.LocalDate;

/**
 * Created by USER on 13/12/2017.
 */

public class Employee {

    //properties:
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthdate;
    private String password;
    private String phone;
    private String city;
    private String district;
    private String cv;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String email, LocalDate birthdate, String password, String phone, String city, String district, String cv) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthdate = birthdate;
        this.password = password;
        this.phone = phone;
        this.city = city;
        this.district = district;
        this.cv = cv;
    }

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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
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

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "birthdate=" + birthdate +
                ", city='" + city + '\'' +
                ", cv='" + cv + '\'' +
                ", district='" + district + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }


    public double getAge(){
        LocalDate now = LocalDate.now();

        double years = now.getYear() - birthdate.getYear();

        double month = (now.getMonthOfYear() - birthdate.getMonthOfYear())/12;


        return years + month;
    }

    public String getAgeString(){
        LocalDate now = LocalDate.now();

        double years = now.getYear() - birthdate.getYear();

        double month = (now.getMonthOfYear() - birthdate.getMonthOfYear())/12;


        return String.format("%2.1f", years + month);
    }
}