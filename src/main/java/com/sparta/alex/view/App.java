package com.sparta.alex.view;

import com.sparta.alex.controller.EmployeeDAO;
import com.sparta.alex.model.Employee;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        EmployeeDAO employeeDTO = new EmployeeDAO();
        employeeDTO.encapsulateData();
        employeeDTO.createTable();


//        for(Map.Entry<Integer, Employee> entry : employeeDTO.employees.entrySet()){
//            System.out.println(entry.getKey() + " " +
//                    entry.getValue().getPrefix() + " " +
//                    entry.getValue().getFirstName() + " " +
//                    entry.getValue().getMiddleIntial() + " " +
//                    entry.getValue().getLastName() + " " +
//                    entry.getValue().getGender() + " " +
//                    entry.getValue().getEmail() + " " +
//                    entry.getValue().getBirthDate() + " " +
//                    entry.getValue().getJoinDate() + " " +
//                    entry.getValue().getSalary()
//            );
//        }
        try {
            employeeDTO.insertEntires(employeeDTO.employees);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
