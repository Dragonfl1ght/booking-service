package org.example.model;


import lombok.Data;

import java.time.Instant;

@Data
public class Booking {
    private Integer id;
    private Integer userId;
    private Integer meetingId;
    private Instant createDateTime;
}
