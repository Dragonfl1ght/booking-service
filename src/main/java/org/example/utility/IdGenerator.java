package org.example.utility;

import org.springframework.stereotype.Component;

@Component
public class IdGenerator {
    private Integer idCounter = 0;

    public Integer nextId() {
        return idCounter++;
    }
}

