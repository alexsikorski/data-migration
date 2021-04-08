package com.sparta.alex.view;

import com.sparta.alex.controller.EmployeeDTO;
import com.sparta.alex.model.Employee;

import java.util.Map;

public class App
{
    public static void main( String[] args )
    {
        EmployeeDTO employeeDTO = new EmployeeDTO();
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

        for(Map.Entry<Integer, Employee> entry : employeeDTO.employees.entrySet()){
            employeeDTO.insertEntry(entry.getKey(), entry.getValue());
        }
    }
}
