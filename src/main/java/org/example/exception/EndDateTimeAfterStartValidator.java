package org.example.exception;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.model.EndDateTimeAfterStart;
import org.example.model.Meeting;

import java.time.Instant;

public class EndDateTimeAfterStartValidator implements ConstraintValidator<EndDateTimeAfterStart, Meeting> {
    @Override
    public boolean isValid(Meeting meeting, ConstraintValidatorContext context) {
        
        if (meeting == null) {
            return false;
        }

        Instant startTime = meeting.getStartTime();
        Instant endTime = meeting.getEndTime();

        if (startTime == null || endTime == null) {
            return false;
        }
        
        return startTime.isBefore(endTime);
    }
}
