package com.sparta.alex.controller;

import com.sparta.alex.model.Employee;

import java.sql.SQLException;
import java.util.HashMap;

public class EmployeeDAOThread implements Runnable {
    EmployeeDAO employeeDAO;
    HashMap<Integer, Employee> splitHashMap;

    public EmployeeDAOThread(HashMap<Integer, Employee> splitHashMap) {
        this.splitHashMap = splitHashMap;
    }

    @Override
    public void run() {
        employeeDAO = new EmployeeDAO();
        insertEntries(splitHashMap);
    }

    private void insertEntries(HashMap<Integer, Employee> splitHashMap) {
        try {
            employeeDAO.insertEntires(splitHashMap);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
