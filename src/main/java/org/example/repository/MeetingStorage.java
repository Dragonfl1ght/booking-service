package org.example.repository;

import org.example.model.Meeting;

import java.util.Optional;

public interface MeetingStorage {
    Meeting create(Meeting meeting);
    Optional<Meeting> findById(Integer id);
    Meeting update(Meeting meeting);
    void delete(int id);
}
