package com.sparta.alex.view;

import com.sparta.alex.controller.EmployeeDAO;
import com.sparta.alex.controller.Reader;
import com.sparta.alex.controller.ThreadController;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {
        Reader reader = new Reader();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        ThreadController threadController = new ThreadController();

        threadController.insertSingleThread(reader.getEmployees());
        employeeDAO.truncateData("employees");
        threadController.createThreads(reader.getEmployees(), 50);
    }
}
