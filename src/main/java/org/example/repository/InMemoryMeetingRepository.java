package org.example.repository;

import lombok.RequiredArgsConstructor;
import org.example.model.Meeting;
import org.example.utility.IdGenerator;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
@Component
@RequiredArgsConstructor
public class InMemoryMeetingRepository implements MeetingStorage {
    private final ConcurrentHashMap<Integer, Meeting> idToMeeting = new ConcurrentHashMap<>();
    private final IdGenerator idGenerator;

    @Override
    public Meeting create(Meeting meeting) {
        meeting.setId(idGenerator.nextId());
        idToMeeting.put(meeting.getId(), meeting);
        return meeting;
    }

    @Override
    public Optional<Meeting> findById(Integer id) {
        return Optional.ofNullable(idToMeeting.get(id));
    }

    @Override
    public Meeting update(Meeting meeting) {
        idToMeeting.put(meeting.getId(), meeting);
        return meeting;
    }

    @Override
    public void delete(int id) {
        idToMeeting.remove(id);
    }
}
