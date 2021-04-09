package com.sparta.alex;


import com.sparta.alex.controller.EmployeeDAO;
import com.sparta.alex.controller.Reader;
import com.sparta.alex.controller.ThreadController;
import com.sparta.alex.util.TestTimer;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class performanceTests {

    private static EmployeeDAO employeeDAO;
    private static Reader reader;
    private static ThreadController threadController;

    private static final int SMALL = 5;
    private static final int MEDIUM = 15;
    private static final int LARGE = 50;
    private static final int FACTOR = 61;

    private static String resultSingle = "";
    private static String resultSmall = "";
    private static String resultMedium = "";
    private static String resultLarge = "";
    private static String resultFactor = "";

    @BeforeEach
    void truncateAll(){
        employeeDAO.truncateData("employee");
    }
    @BeforeAll
    static void init(){
        reader = new Reader();
        employeeDAO = new EmployeeDAO();
        threadController = new ThreadController();
    }

    @AfterAll
    static void printResults() {
        System.out.println("-- Performance testing for insertions --\n" + resultSingle);
        System.out.println(resultSmall);
        System.out.println(resultMedium);
        System.out.println(resultLarge);
        System.out.println(resultFactor);
    }

    @Test
    void singleThreadTest() {
        TestTimer timer = new TestTimer();
        timer.start();
        threadController.insertSingleThread(reader.getEmployees());
        float timeTaken = timer.end();
        assertEquals(9943, reader.getEmployees().size());
        resultSingle = "Single Thread: " + timeTaken + "s.";
    }

    @Test
    void multiThreadSmallTest() {
        TestTimer timer = new TestTimer();
        timer.start();
        threadController.createThreads(reader.getEmployees(), SMALL);
        float timeTaken = timer.end();
        assertEquals(9943, reader.getEmployees().size());
        resultSmall = "Multi Thread (" + SMALL + "): " + timeTaken + "s.";
    }

    @Test
    void multiThreadMediumTest() {
        TestTimer timer = new TestTimer();
        timer.start();
        threadController.createThreads(reader.getEmployees(), MEDIUM);
        float timeTaken = timer.end();
        assertEquals(9943, reader.getEmployees().size());
        resultMedium = "Multi Thread (" + MEDIUM + "): " + timeTaken + "s.";
    }

    @Test
    void multiThreadLargeTest() {
        TestTimer timer = new TestTimer();
        timer.start();
        threadController.createThreads(reader.getEmployees(), LARGE);
        float timeTaken = timer.end();
        assertEquals(9943, reader.getEmployees().size());
        resultLarge = "Multi Thread (" + LARGE + "): " + timeTaken + "s.";
    }

    @Test
    void multiThreadFactorTest() {
        TestTimer timer = new TestTimer();
        timer.start();
        threadController.createThreads(reader.getEmployees(), FACTOR);
        float timeTaken = timer.end();
        assertEquals(9943, reader.getEmployees().size());
        resultFactor = "Multi Thread (" + FACTOR + "): " + timeTaken + "s.";
    }
}
