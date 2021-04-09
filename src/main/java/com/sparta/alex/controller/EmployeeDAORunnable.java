package com.sparta.alex.controller;

import com.sparta.alex.model.Employee;

import java.sql.SQLException;
import java.util.HashMap;

public class EmployeeDAORunnable implements Runnable {

    EmployeeDAO employeeDAO;
    public final HashMap<Integer, Employee> splitHashMap;

    public EmployeeDAORunnable(HashMap<Integer, Employee> splitHashMap) {
        this.splitHashMap = new HashMap<Integer, Employee>(splitHashMap);
    }

    @Override
    public void run() {
        employeeDAO = new EmployeeDAO();

        try {
            employeeDAO.insertEntires(splitHashMap);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
