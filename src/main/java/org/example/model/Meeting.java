package org.example.model;

import lombok.Data;

import java.time.Instant;

@Data
public class Meeting {
    private Integer id;
    private Integer userId;
    private Instant startTime;
    private Instant endTime;
    private Status status;
    private Integer maxParticipants;
}
