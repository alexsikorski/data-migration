package com.sparta.alex.view;

import com.sparta.alex.controller.EmployeeDAO;
import com.sparta.alex.controller.Reader;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        EmployeeDAO employeeDTO = new EmployeeDAO();
        Reader reader = new Reader();

        employeeDTO.createTable();

        try {
            employeeDTO.insertEntires(reader.getEmployees());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
