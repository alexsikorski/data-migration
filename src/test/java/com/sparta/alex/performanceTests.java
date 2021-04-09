package com.sparta.alex;


import com.sparta.alex.controller.EmployeeDAO;
import com.sparta.alex.controller.Reader;
import com.sparta.alex.controller.ThreadController;
import com.sparta.alex.util.TestTimer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class performanceTests {

    private static final int SMALL = 5;
    private static final int MEDIUM = 15;
    private static final int LARGE = 50;
    private static final int FACTOR = 61;
    private static final int XLARGE = 100;
    private static EmployeeDAO employeeDAO;
    private static Reader reader;
    private static ThreadController threadController;
    private static String resultSingle = "";
    private static String resultSmall = "";
    private static String resultMedium = "";
    private static String resultLarge = "";
    private static String resultFactor = "";
    private static String resultXLarge = "";

    @BeforeAll
    static void init() {
        reader = new Reader();
        employeeDAO = new EmployeeDAO();
        threadController = new ThreadController();
    }

    @AfterAll
    static void printResults() throws SQLException {
        employeeDAO.truncateData("employees");
        System.out.println("-- Performance testing for insertions --\n" + resultSingle);
        System.out.println(resultSmall);
        System.out.println(resultMedium);
        System.out.println(resultLarge);
        System.out.println(resultFactor);
        System.out.println(resultXLarge);
    }

    @BeforeEach
    void truncateAll() throws SQLException {
        employeeDAO.truncateData("employees");
    }

    @Test
    void singleThreadTest() throws SQLException {
        TestTimer timer = new TestTimer();
        timer.start();
        threadController.insertSingleThread(reader.getEmployees());
        float timeTaken = 0;
        while (employeeDAO.getTableCount("employees") <= reader.getEmployees().size()) {
            if (employeeDAO.getTableCount("employees") == reader.getEmployees().size()) {
                timeTaken = timer.end();
                break;
            }
        }
        assertEquals(9943, reader.getEmployees().size());
        resultSingle = "Single Thread: " + timeTaken + "s.";
    }

    @Test
    void multiThreadSmallTest() throws SQLException {
        TestTimer timer = new TestTimer();
        timer.start();
        threadController.createThreads(reader.getEmployees(), SMALL);
        float timeTaken = 0;
        while (employeeDAO.getTableCount("employees") <= reader.getEmployees().size()) {
            if (employeeDAO.getTableCount("employees") == reader.getEmployees().size()) {
                timeTaken = timer.end();
                break;
            }
        }
        assertEquals(9943, reader.getEmployees().size());
        resultSmall = "Multi Thread (" + SMALL + " + 1): " + timeTaken + "s.";
    }

    @Test
    void multiThreadMediumTest() throws SQLException {
        TestTimer timer = new TestTimer();
        timer.start();
        threadController.createThreads(reader.getEmployees(), MEDIUM);
        float timeTaken = 0;
        while (employeeDAO.getTableCount("employees") <= reader.getEmployees().size()) {
            if (employeeDAO.getTableCount("employees") == reader.getEmployees().size()) {
                timeTaken = timer.end();
                break;
            }
        }
        assertEquals(9943, reader.getEmployees().size());
        resultMedium = "Multi Thread (" + MEDIUM + " + 1): " + timeTaken + "s.";
    }

    @Test
    void multiThreadLargeTest() throws SQLException {
        TestTimer timer = new TestTimer();
        timer.start();
        threadController.createThreads(reader.getEmployees(), LARGE);
        float timeTaken = 0;
        while (employeeDAO.getTableCount("employees") <= reader.getEmployees().size()) {
            if (employeeDAO.getTableCount("employees") == reader.getEmployees().size()) {
                timeTaken = timer.end();
                break;
            }
        }
        assertEquals(9943, reader.getEmployees().size());
        resultLarge = "Multi Thread (" + LARGE + " + 1): " + timeTaken + "s.";
    }

    @Test
    void multiThreadFactorTest() throws SQLException {
        TestTimer timer = new TestTimer();
        timer.start();
        threadController.createThreads(reader.getEmployees(), FACTOR);
        float timeTaken = 0;
        while (employeeDAO.getTableCount("employees") <= reader.getEmployees().size()) {
            if (employeeDAO.getTableCount("employees") == reader.getEmployees().size()) {
                timeTaken = timer.end();
                break;
            }
        }
        assertEquals(9943, reader.getEmployees().size());
        resultFactor = "Multi Thread (" + FACTOR + "): " + timeTaken + "s.";
    }

    @Test
    void multiThreadXLargeTest() throws SQLException {
        TestTimer timer = new TestTimer();
        timer.start();
        threadController.createThreads(reader.getEmployees(), XLARGE);
        float timeTaken = 0;
        while (employeeDAO.getTableCount("employees") <= reader.getEmployees().size()) {
            if (employeeDAO.getTableCount("employees") == reader.getEmployees().size()) {
                timeTaken = timer.end();
                break;
            }
        }
        assertEquals(9943, reader.getEmployees().size());
        resultXLarge = "Multi Thread (" + XLARGE + " + 1): " + timeTaken + "s.";
    }
}
