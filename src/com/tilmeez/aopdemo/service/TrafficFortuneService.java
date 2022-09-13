package com.tilmeez.aopdemo.service;

import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class TrafficFortuneService {

    public String getFortune() {
        try {
            // simulate a delay
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // return a fortune
        return "Except heavy traffic this morning";
    }

    public String getFortune(boolean tripWire) {
        if (tripWire) {
            throw new RuntimeException("Major accident! Highway is closed");
        }
        return getFortune();
    }
}
