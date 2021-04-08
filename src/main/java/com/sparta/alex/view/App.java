package com.sparta.alex.view;

import com.sparta.alex.controller.EmployeeDTO;

public class App
{
    public static void main( String[] args )
    {
        System.out.println(new EmployeeDTO().connectToDatabase());
    }
}
