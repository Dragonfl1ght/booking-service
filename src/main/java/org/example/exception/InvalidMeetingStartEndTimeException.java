package org.example.exception;

public class InvalidMeetingStartEndTimeException extends RuntimeException {
    public InvalidMeetingStartEndTimeException(String message) {
        super(message);
    }
}
