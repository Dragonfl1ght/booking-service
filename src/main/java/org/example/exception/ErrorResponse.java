package org.example.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private String code;
    private Instant dateTime;
}

