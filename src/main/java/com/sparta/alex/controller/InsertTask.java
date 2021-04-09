package com.sparta.alex.controller;

import com.sparta.alex.model.Employee;

import java.sql.SQLException;
import java.util.HashMap;

public class InsertTask implements Runnable {

    public final HashMap<Integer, Employee> splitHashMap;
    EmployeeDAO employeeDAO;

    public InsertTask(HashMap<Integer, Employee> splitHashMap) {
        this.splitHashMap = new HashMap<>(splitHashMap);
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
