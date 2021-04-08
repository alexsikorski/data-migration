package com.sparta.alex.view;

import com.sparta.alex.controller.EmployeeDTO;

public class App
{
    public static void main( String[] args )
    {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.encapsulateData();
        employeeDTO.createTable();
    }
}
