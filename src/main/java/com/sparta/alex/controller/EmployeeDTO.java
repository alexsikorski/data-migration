package com.sparta.alex.controller;

import com.sparta.alex.model.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

public class EmployeeDTO implements DTO {

    private static final String FILE_PATH = "resources/employees.csv";
    private static final Properties PROPERTIES = new Properties();
    static HashMap<Integer, Employee> employees = new HashMap<>();
    static HashMap<Integer, Employee> toBeReviewedEmployees = new HashMap<>();
    private Connection connection;

    @Override
    public void encapsulateData() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // first line contains column names so we ignore it
                if (!firstLine) {
                    // obtained lines, now to encapsulate data and populate hashmap
                    if (employees.containsKey(Integer.parseInt(values[0]))) {
                        // if employee already exists, add to toBeReviewedEmployees
                        toBeReviewedEmployees.put(Integer.parseInt(values[0]), new Employee(values[1], values[2], values[3].charAt(0), values[4], values[5].charAt(0), values[6], values[7], values[8], Integer.parseInt(values[9])));
                    } else {
                        employees.put(Integer.parseInt(values[0]), new Employee(values[1], values[2], values[3].charAt(0), values[4], values[5].charAt(0), values[6], values[7], values[8], Integer.parseInt(values[9])));
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
            PROPERTIES.load(new FileReader("resources/login.properties"));
            String URL = "jdbc:mysql://localhost:3306/myLocalDb";
            connection = DriverManager.getConnection(URL, PROPERTIES.getProperty("username"), PROPERTIES.getProperty("password"));
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    @Override
    public void createTable() {
        String createTableQuery = "CREATE TABLE employees ("
                + "employee_id INT NOT NULL,"
                + "prefix VARCHAR(5),"
                + "first_name VARCHAR(45),"
                + "middle_initial VARCHAR(1),"
                + "last_name VARCHAR(45),"
                + "gender VARCHAR(1),"
                + "email VARCHAR(45),"
                + "date_of_birth DATE,"
                + "date_of_joining DATE,"
                + "salary INT,"
                + "PRIMARY KEY (employee_id)"
                + ")";

        try {
            PreparedStatement preparedStatement = connectToDatabase().prepareStatement(createTableQuery);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void insertEntries() {

    }
}
