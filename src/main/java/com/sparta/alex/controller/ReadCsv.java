package com.sparta.alex.controller;

import com.sparta.alex.model.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ReadCsv {

    private static final String filePath = "resources/employees.csv";
    static HashMap<Integer, Employee> employees = new HashMap<>();
    static HashMap<Integer, Employee> toBeReviewedEmployees = new HashMap<>();

    public static void readCsvFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // first line contains column names so we ignore it
                if (!firstLine) {
                    // obtained lines, now to encapsulate data and populate hashmap
                    if (employees.containsKey(Integer.parseInt(values[0]))) {
                        // if employee already exists, add to toBeReviewedEmployees
                        toBeReviewedEmployees.put(Integer.parseInt(values[0]), new Employee(values[1], values[2], values[3], values[4], values[5].charAt(0), values[6], values[7], values[8], Integer.parseInt(values[9])));
                    } else {
                        employees.put(Integer.parseInt(values[0]), new Employee(values[1], values[2], values[3], values[4], values[5].charAt(0), values[6], values[7], values[8], Integer.parseInt(values[9])));
                    }
                }
                firstLine = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
