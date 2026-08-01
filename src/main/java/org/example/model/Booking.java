package org.example.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.Instant;

@Data
public class Booking {
    private Integer id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotNull private Integer meetingId;

    private Instant createDateTime;
}
