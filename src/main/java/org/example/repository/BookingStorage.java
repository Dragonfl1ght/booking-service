package org.example.repository;

import org.example.model.Booking;

import java.util.Optional;

public interface BookingStorage {
    Booking create(Booking meeting);
    Optional<Booking> findById(Integer id);
    void delete(int id);
}
