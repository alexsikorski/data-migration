package com.sparta.alex.controller;

import com.sparta.alex.model.Employee;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class EmployeeDAO implements DAO {

    private static final Properties PROPERTIES = new Properties();
    String insertEntry = "INSERT INTO employees VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    @Override
    public Connection connectToDatabase() {
        Connection connection = null;
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
    public void createTable() throws SQLException {
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
        } finally {
            connectToDatabase().close();
        }
    }

    @Override
    public void insertEntry(Integer employeeId, Employee employee, PreparedStatement preparedStatement) {
        try {
            preparedStatement.setInt(1, employeeId);
            preparedStatement.setString(2, employee.getPrefix());
            preparedStatement.setString(3, employee.getFirstName());
            preparedStatement.setString(4, Character.toString(employee.getMiddleInitial()));
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
    public void insertEntries(HashMap<Integer, Employee> employeeHashMap) throws SQLException {
        PreparedStatement preparedStatement = connectToDatabase().prepareStatement(insertEntry);
        for (Map.Entry<Integer, Employee> entry : employeeHashMap.entrySet()) {
            insertEntry(entry.getKey(), entry.getValue(), preparedStatement);
        }
        connectToDatabase().close();
    }

    @Override
    public void truncateData(String tableName) throws SQLException {
        try {
            PreparedStatement preparedStatement = connectToDatabase().prepareStatement("TRUNCATE " + tableName + ";");
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectToDatabase().close();
        }
    }

    @Override
    public int getTableCount(String tableName) throws SQLException {
        try {
            Statement statement = connectToDatabase().createStatement();
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM " + tableName + ";");
            rs.next();
            return rs.getInt("COUNT(*)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectToDatabase().close();
        }
        return 0;
    }
}
