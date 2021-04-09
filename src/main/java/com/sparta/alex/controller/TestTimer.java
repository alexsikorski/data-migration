package com.sparta.alex.controller;

public class TestTimer {
    private long start;

    public void start() {
        start = System.nanoTime();
    }

    public float end() {
        return (System.nanoTime() - start) / 1000000000f;
    }
}
