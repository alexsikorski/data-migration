package com.sparta.alex.controller;

import com.sparta.alex.model.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class EmployeeDAO implements DAO {

    private static final String FILE_PATH = "resources/employees.csv";
    private static final Properties PROPERTIES = new Properties();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    String insertEntry = "INSERT INTO employees VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    public final HashMap<Integer, Employee> employees = new HashMap<>();
    public final HashMap<Integer, Employee> toBeReviewedEmployees = new HashMap<>();
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
                        toBeReviewedEmployees.put(Integer.parseInt(values[0]), new Employee(values[1], values[2], values[3].charAt(0), values[4], values[5].charAt(0), values[6], dateFormat.parse(values[7]), dateFormat.parse(values[8]), Integer.parseInt(values[9])));
                    } else {
                        employees.put(Integer.parseInt(values[0]), new Employee(values[1], values[2], values[3].charAt(0), values[4], values[5].charAt(0), values[6], dateFormat.parse(values[7]), dateFormat.parse(values[8]), Integer.parseInt(values[9])));
                    }
                }
                firstLine = false;
            }
        } catch (IOException | ParseException e) {
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
                + "middle_initial CHAR(1),"
                + "last_name VARCHAR(45),"
                + "gender CHAR(1),"
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
    public void insertEntry(Integer employeeId, Employee employee, PreparedStatement preparedStatement) {
        try {
            preparedStatement.setInt(1, employeeId);
            preparedStatement.setString(2, employee.getPrefix());
            preparedStatement.setString(3, employee.getFirstName());
            preparedStatement.setString(4, Character.toString(employee.getMiddleIntial()));
            preparedStatement.setString(5, employee.getLastName());
            preparedStatement.setString(6, Character.toString(employee.getGender()));
            preparedStatement.setString(7, employee.getEmail());
            preparedStatement.setDate(8, new java.sql.Date(employee.getBirthDate().getTime()));
            preparedStatement.setDate(9, new java.sql.Date(employee.getJoinDate().getTime()));
            preparedStatement.setInt(10, employee.getSalary());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void insertEntires(HashMap<Integer, Employee> employeeHashMap) throws SQLException {
        PreparedStatement preparedStatement = connectToDatabase().prepareStatement(insertEntry);
        for (Map.Entry<Integer, Employee> entry : employees.entrySet()) {
            insertEntry(entry.getKey(), entry.getValue(), preparedStatement);
        }
    }
}