package com.hackon.resources;

import com.hackon.entities.Event;
import com.hackon.services.EventService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<Event> create(@RequestBody Event event) {
        Event eventCreated = eventService.create(event);

        return new ResponseEntity<Event>(eventCreated, HttpStatusCode.valueOf(201));

    }


}
