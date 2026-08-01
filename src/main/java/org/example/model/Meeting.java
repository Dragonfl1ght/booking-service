package org.example.model;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@EndDateTimeAfterStart
public class Meeting {
    private Integer id;
    @NotNull private Integer userId;
    @Future private Instant startTime;
    @Future private Instant endTime;
    private Status status;
    @Max(8)
    @Min(1)
    private Integer maxParticipants;
    private List<Booking> bookings = new ArrayList<>();
}
