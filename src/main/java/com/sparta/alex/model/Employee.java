package com.sparta.alex.model;


public class Employee {

    private String prefix;
    private String firstName;
    private String middleName;
    private String lastName;
    private char gender;
    private String email;
    private String birthDate;
    private String joinDate;
    private int salary;

    public Employee(String prefix, String firstName, String middleName, String lastName, char gender, String email, String birthDate, String joinDate, int salary) {
        this.prefix = prefix;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.birthDate = birthDate;
        this.joinDate = joinDate;
        this.salary = salary;
    }
}
