package com.hackon.controller;

import com.hackon.dto.EventDto;
import com.hackon.dto.request.CreateEventDto;
import com.hackon.exception.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import com.hackon.dto.UpdateEventDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackon.entities.Event;
import com.hackon.services.EventService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<EventDto>> listAll(){
        return ResponseEntity.ok(eventService.findAll());
    }

    @PostMapping()
    public ResponseEntity<EventDto> create(@Valid @RequestBody CreateEventDto eventDto) {
        EventDto eventCreated = eventService.create(eventDto);
        return new ResponseEntity<>(eventCreated, HttpStatusCode.valueOf(201));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws NotFoundException {
        eventService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDto> getEvent(@PathVariable Long id) throws NotFoundException {
        EventDto event = eventService.getEvent(id);
        return ResponseEntity.ok(event);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UpdateEventDto dto){
        eventService.update(id, dto);
        return ResponseEntity.ok().build();
    }
}
