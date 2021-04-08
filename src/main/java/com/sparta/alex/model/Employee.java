package com.sparta.alex.model;


public class Employee {

    private String prefix;
    private String firstName;
    private char middleIntial;
    private String lastName;
    private char gender;
    private String email;
    private String birthDate;
    private String joinDate;
    private int salary;

    public Employee(String prefix, String firstName, char middleIntial, String lastName, char gender, String email, String birthDate, String joinDate, int salary) {
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
}
