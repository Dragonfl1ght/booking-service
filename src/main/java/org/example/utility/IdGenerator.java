package org.example.utility;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class IdGenerator {
    private AtomicInteger idCounter = new AtomicInteger(0);;

    public Integer nextId() {
        return idCounter.getAndIncrement();
    }
}

