package com.sparta.alex.model;

import java.util.Date;

public class Employee {

    private final String prefix;
    private final String firstName;
    private final char middleInitial;
    private final String lastName;
    private final char gender;
    private final String email;
    private final Date birthDate;
    private final Date joinDate;
    private final int salary;

    public Employee(String prefix, String firstName, char middleInitial, String lastName, char gender, String email, Date birthDate, Date joinDate, int salary) {
        this.prefix = prefix;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
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

    public char getMiddleInitial() {
        return middleInitial;
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
