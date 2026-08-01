package org.example.repository;

import lombok.RequiredArgsConstructor;
import org.example.model.Booking;
import org.example.utility.IdGenerator;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class InMemoryBookingRepository implements BookingStorage {
    private final ConcurrentHashMap<Integer, Booking> idToBooking = new ConcurrentHashMap<>();
    private final IdGenerator idGenerator;

    @Override
    public Booking create(Booking meeting) {
        meeting.setId(idGenerator.nextId());
        idToBooking.put(meeting.getId(), meeting);
        return meeting;
    }

    @Override
    public Optional<Booking> findById(Integer id) {
        return Optional.ofNullable(idToBooking.get(id));
    }

    @Override
    public void delete(int id) {
        idToBooking.remove(id);
    }

}
