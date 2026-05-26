package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.Meeting;
import org.example.model.Status;
import org.example.repository.MeetingStorage;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeetingService {
    private final MeetingStorage repository;

    public Meeting create (Meeting meeting){
        meeting.setStatus(Status.AVAILABLE);
        return repository.create(meeting);
    }

    public Meeting update(Meeting meeting){
        var findById = repository.findById(meeting.getId())
                .orElseThrow(() -> new RuntimeException("Meeting not found" + meeting.getId()));
        //etc
        return repository.update(meeting);
    }

    public void delete (int id){

    }
}
