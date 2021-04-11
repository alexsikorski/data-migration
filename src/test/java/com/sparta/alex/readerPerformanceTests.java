package com.sparta.alex;

import com.sparta.alex.controller.Reader;
import com.sparta.alex.util.TestTimer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class readerPerformanceTests {

    private static Reader reader = null;
    private static String resultRead = "";

    @AfterAll
    static void printResults() {
        System.out.println("-- Performance testing for reading --\n" + resultRead);
    }

    @Test
    void performanceTest() {
        TestTimer timer = new TestTimer();
        timer.start();
        reader = new Reader(); // instance starts reading upon constructing
        float timeTaken = timer.end();
        assertEquals(9943, reader.getEmployees().size());
        assertEquals(57, reader.getToBeReviewedEmployees().size());
        resultRead = "Reading Time: " + timeTaken + "s.";
    }
}
