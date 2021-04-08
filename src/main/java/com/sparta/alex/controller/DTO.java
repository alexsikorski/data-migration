package com.sparta.alex.controller;

import java.sql.Connection;

public interface DTO {
    void encapsulateData();
    Connection connectToDatabase();
    void createTable();
    void insertEntries();
}
