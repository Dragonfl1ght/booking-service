package org.example.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.exception.MeetingConflictException;
import org.example.exception.MeetingNotFoundException;
import org.example.exception.UserNotFoundException;
import org.example.model.Booking;
import org.example.model.Meeting;
import org.example.model.Status;
import org.example.model.User;
import org.example.repository.InMemoryUserRepository;
import org.example.repository.MeetingStorage;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class MeetingService {
    private final MeetingStorage meetingRepository;
    private final InMemoryUserRepository userRepository;
    private final ConcurrentHashMap<Integer, Object> userLocks = new ConcurrentHashMap<>();

    public Meeting create(Meeting meeting) {
        log.debug("Выполняется создание встречи {}", meeting);
        User user = userRepository.findById(meeting.getUserId())
                .orElseThrow(() -> new UserNotFoundException(String.format("При создании встречи не найден организатор с id = %s", meeting.getUserId())));

        Instant newStart = meeting.getStartTime();
        Instant newEnd = meeting.getEndTime();

        Object lock = userLocks.computeIfAbsent(user.getId(), k -> new Object());

        Meeting newMeeting;

        synchronized (lock) {
            List<Meeting> allByOwnerId = findAllByOwnerId(user.getId());
            for (Meeting m : allByOwnerId) {
                var mStart = m.getStartTime();
                var mEnd = m.getEndTime();

                if (newEnd.isAfter(mStart) && newStart.isBefore(mEnd)) {
                    throw new MeetingConflictException(
                            String.format("Встреча пересекается с существующей встречей id=%d [%s - %s]",
                                    m.getId(), m.getStartTime(), m.getEndTime())
                    );
                }
            }

            meeting.setStatus(Status.AVAILABLE);

            newMeeting = meetingRepository.create(meeting);

            log.debug("Встреча успешно создана {}", meeting);
        }

        return newMeeting;
    }

    public List<Meeting> findAllByOwnerId(Integer id) {
        return meetingRepository.findAllByOwner(id);
    }

    public Meeting update(Meeting meeting) {
        var findById = meetingRepository.findById(meeting.getId())
                .orElseThrow(() -> new RuntimeException("Meeting not found" + meeting.getId()));
        //etc
        return meetingRepository.update(meeting);
    }


    public void delete(int id) {

    }

    public Booking book(@Valid Booking booking) {
        Integer meetingId = booking.getMeetingId();
        Meeting meeting = meetingRepository.findById(meetingId).orElseThrow(() -> new MeetingNotFoundException(String.format("При бронировании не найдена встреча с id = %s", booking.getMeetingId())));

    }
}
