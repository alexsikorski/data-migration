package com.sparta.alex.controller;

import com.sparta.alex.model.Employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class Reader {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    private final HashMap<Integer, Employee> employees = new HashMap<>();
    private final HashMap<Integer, Employee> toBeReviewedEmployees = new HashMap<>();

    public Reader() {
        encapsulateData();
    }

    private void encapsulateData() {
        String filePath = "resources/employees.csv";
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(filePath))) {
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

    public HashMap<Integer, Employee> getEmployees() {
        return employees;
    }

    public HashMap<Integer, Employee> getToBeReviewedEmployees() {
        return toBeReviewedEmployees;
    }
}
