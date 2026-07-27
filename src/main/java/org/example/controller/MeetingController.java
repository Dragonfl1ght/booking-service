package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Meeting;
import org.example.service.MeetingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Valid
@RestController
@RequestMapping("meetings")
@RequiredArgsConstructor
public class MeetingController {
    private final MeetingService service;
    @PostMapping
    public ResponseEntity<Meeting> create(@RequestBody @Valid Meeting meeting){
        log.info("Получен запрос на создание встречи {}", meeting);
        Meeting newMeeting = service.create(meeting);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMeeting);
    }
}
