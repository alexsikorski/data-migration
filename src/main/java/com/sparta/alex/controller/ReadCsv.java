package com.sparta.alex.controller;

import com.sparta.alex.model.Employee;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ReadCsv {

    HashMap<Integer, Employee> employees = new HashMap<>();

    private static final String filePath = "resources/employees.csv";

    public static void readCsvFile(){
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
