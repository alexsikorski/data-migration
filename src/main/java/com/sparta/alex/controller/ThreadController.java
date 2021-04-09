package com.sparta.alex.controller;

import com.sparta.alex.model.Employee;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ThreadController {

    public void insertSingleThread(HashMap<Integer, Employee> employees) {
        EmployeeDAO employeeDTO = new EmployeeDAO();
        try {
            employeeDTO.insertEntires(employees);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createThreads(HashMap<Integer, Employee> employees, int numberOfThreads) {
        int size = employees.size();
        int numberOfEntriesPerThread = size / numberOfThreads;
        int iterCount = 0;

        HashMap<Integer, Employee> tempHashMap = new HashMap<>();

        for (Map.Entry<Integer, Employee> entry : employees.entrySet()) {
            iterCount += 1;
            tempHashMap.put(entry.getKey(), entry.getValue());
            // check every segment
            if (iterCount % numberOfEntriesPerThread == 0 && iterCount != 0) {
                // System.out.println("SPLIT " + iterCount);
                new Thread(new InsertTask(tempHashMap)).start();
                // System.out.println(tempHashMap.size());
                tempHashMap.clear();
            }
            // due to rounding, check if entry == last in database, create extra thread to deal with last entries
            else if (iterCount == size) {
                // System.out.println("last entry! " + iterCount);

                new Thread(new InsertTask(tempHashMap)).start();

                // System.out.println(tempHashMap.size());
                tempHashMap.clear();
            }
        }
    }
}
