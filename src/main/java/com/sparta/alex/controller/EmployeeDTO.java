package com.sparta.alex.controller;

import com.sparta.alex.model.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

public class EmployeeDTO implements DTO {

    private static final String filePath = "resources/employees.csv";
    private static final Properties properties = new Properties();
    static HashMap<Integer, Employee> employees = new HashMap<>();
    static HashMap<Integer, Employee> toBeReviewedEmployees = new HashMap<>();
    private Connection connection;

    @Override
    public void encapsulateData() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // first line contains column names so we ignore it
                if (!firstLine) {
                    // obtained lines, now to encapsulate data and populate hashmap
                    if (employees.containsKey(Integer.parseInt(values[0]))) {
                        // if employee already exists, add to toBeReviewedEmployees
                        toBeReviewedEmployees.put(Integer.parseInt(values[0]), new Employee(values[1], values[2], values[3], values[4], values[5].charAt(0), values[6], values[7], values[8], Integer.parseInt(values[9])));
                    } else {
                        employees.put(Integer.parseInt(values[0]), new Employee(values[1], values[2], values[3], values[4], values[5].charAt(0), values[6], values[7], values[8], Integer.parseInt(values[9])));
                    }
                }
                firstLine = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection connectToDatabase() {
        try {
            properties.load(new FileReader("resources/login.properties"));
            String URL = "jdbc:mysql://localhost:3306/myLocalDb";
            connection = DriverManager.getConnection(URL, properties.getProperty("username"), properties.getProperty("password"));
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    @Override
    public void createTable() {

    }

    @Override
    public void insertEntries() {

    }
}
