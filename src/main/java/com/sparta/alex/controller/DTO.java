package com.sparta.alex.controller;

import com.sparta.alex.model.Employee;

import java.sql.Connection;

public interface DTO {
    void encapsulateData();
    Connection connectToDatabase();
    void createTable();
    void insertEntry(Integer employeeId, Employee employee);
}
