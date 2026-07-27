package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.exception.UserNotFoundException;
import org.example.model.Meeting;
import org.example.model.Status;
import org.example.repository.InMemoryUserRepository;
import org.example.repository.MeetingStorage;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MeetingService {
    private final MeetingStorage meetingRepository;
    private final InMemoryUserRepository userRepository;

    public Meeting create(Meeting meeting) {
        log.debug("Выполняется создание встречи {}", meeting);
        userRepository.findById(meeting.getUserId())
                .orElseThrow(() -> new UserNotFoundException(String.format("При создании встречи не найден организатор с id = %s", meeting.getUserId())));

//        if (meeting.getStartTime().isAfter(meeting.getEndTime())) {
//            throw new InvalidMeetingStartEndTimeException(String.format("Время завершения встречи %s не может быть раньше времени начала", meeting));
//        }

        meeting.setStatus(Status.AVAILABLE);

        log.debug("Встреча успешно создана {}", meeting);

        return meetingRepository.create(meeting);
    }

    public List<Meeting> findAllByOwnerId(Integer id){
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
}
