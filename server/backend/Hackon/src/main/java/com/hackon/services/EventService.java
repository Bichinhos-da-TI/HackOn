package com.hackon.services;

import com.hackon.entities.Event;
import com.hackon.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event create(Event event) {
        if(!event.getStartDateTime().isBefore(event.getEndDateTime())){
            throw new IllegalArgumentException("Event Start date must be before End date");
        }

        if(!eventRepository.findEventsBetween(event.getStartDateTime(), event.getEndDateTime(), event.getLocation()).isEmpty()){
                throw new RuntimeException("Event already exists at this location and this time");
        }
        if(Duration.between(event.getStartDateTime(), event.getEndDateTime()).toHours() < 1 || Duration.between(event.getStartDateTime(), event.getEndDateTime()).toHours() > 48){
            throw new RuntimeException("The minimum event duration is 1 hour, and the maximum event duration is 48 hours");
        }


        return eventRepository.save(event);

    }

}
