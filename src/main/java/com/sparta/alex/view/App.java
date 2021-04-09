package com.sparta.alex.view;

import com.sparta.alex.controller.EmployeeDAO;
import com.sparta.alex.controller.EmployeeDAOThread;
import com.sparta.alex.controller.Reader;
import com.sparta.alex.controller.TestTimer;
import com.sparta.alex.model.Employee;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        Reader reader = new Reader();
        //employeeDTO.createTable();


        //insertSingleThread(reader.getEmployees());
        createThreads(reader.getEmployees(), 5);
    }

    private static void insertSingleThread(HashMap<Integer, Employee> employees) {
        EmployeeDAO employeeDTO = new EmployeeDAO();


        TestTimer timer = new TestTimer();
        timer.start();

        try {
            employeeDTO.insertEntires(employees);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        float timeTaken = timer.end();
        System.out.println(timeTaken);
    }

    private static void createThreads(HashMap<Integer, Employee> employees, int numberOfThreads) {
        int size = employees.size();
        int numberOfEntriesPerThread = size / numberOfThreads;
        int iterCount = 0;

        HashMap<Integer, Employee> tempHashMap = new HashMap<>();

        TestTimer timer = new TestTimer();
        timer.start();
        for (Map.Entry<Integer, Employee> entry : employees.entrySet()) {
            iterCount += 1;
            tempHashMap.put(entry.getKey(), entry.getValue());
            // check every segment
            if (iterCount % numberOfEntriesPerThread == 0 && iterCount != 0) {
                System.out.println("SPLIT " + iterCount);
                new EmployeeDAOThread(tempHashMap).run();
                tempHashMap.clear();
            }
            // due to rounding, check if entry == last in database, create extra thread to deal with last entries
            else if (iterCount == size) {
                System.out.println("last entry! " + iterCount);
                new EmployeeDAOThread(tempHashMap).run();
                tempHashMap.clear();
            }
        }
        float timeTaken = timer.end();
        System.out.println(timeTaken);
    }
}
