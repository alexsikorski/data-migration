package com.sparta.alex.controller;

import com.sparta.alex.model.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ReadCsv {

    static HashMap<Integer, Employee> employees = new HashMap<>();

    private static final String filePath = "resources/employees.csv";

    public static void readCsvFile(){
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (!firstLine){
                    // obtained lines, now to  encapsulate data and populate hashmap
                    System.out.println(Arrays.toString(values));
                    employees.put(Integer.parseInt(values[0]), new Employee(values[1], values[2], values[3], values[4], values[5].charAt(0), values[6], values[7], values[8], Integer.parseInt(values[9])));
                }
                firstLine = false;
            }

            for(Map.Entry<Integer, Employee> entry : employees.entrySet()){
                System.out.println(entry.getKey() + " " + entry.getValue());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
