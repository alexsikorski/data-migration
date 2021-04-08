package com.sparta.alex.controller;

import com.sparta.alex.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public interface DAO {
    void encapsulateData();

    Connection connectToDatabase();

    void createTable();

    void insertEntry(Integer employeeId, Employee employee, PreparedStatement preparedStatement);

    void insertEntires(HashMap<Integer, Employee> employeeHashMap) throws SQLException;
}
