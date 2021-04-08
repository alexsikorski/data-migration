package com.sparta.alex.model;


import java.util.Date;

public class Employee {

    private String prefix;
    private String firstName;
    private char middleIntial;
    private String lastName;
    private char gender;
    private String email;
    private Date birthDate;
    private Date joinDate;
    private int salary;

    public Employee(String prefix, String firstName, char middleIntial, String lastName, char gender, String email, Date birthDate, Date joinDate, int salary) {
        this.prefix = prefix;
        this.firstName = firstName;
        this.middleIntial = middleIntial;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.birthDate = birthDate;
        this.joinDate = joinDate;
        this.salary = salary;
    }


    public String getPrefix() {
        return prefix;
    }

    public String getFirstName() {
        return firstName;
    }

    public char getMiddleIntial() {
        return middleIntial;
    }

    public String getLastName() {
        return lastName;
    }

    public char getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public int getSalary() {
        return salary;
    }
}
