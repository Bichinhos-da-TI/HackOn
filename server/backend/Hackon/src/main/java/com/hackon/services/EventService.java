package com.hackon.services;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hackon.entities.Event;
import com.hackon.repositories.EventRepository;


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
        if(eventRepository.findAllByUserIdAndMonth(event.getUserId(), event.getStartDateTime().getMonth().getValue(), event.getStartDateTime().getYear()).size() >= 5){
            throw new RuntimeException("You have already created 5 events this mounth");
        }


        return eventRepository.save(event);

    }


    public ResponseEntity<?> delete(Long id){
        var eventDelete = eventRepository.findById(id);

        //#TODO tem que fazer a validação de autorizacao quando tiver a entidade de usuarios
        //#TODO verificacao de desafio em_andamento ou concluido quando tiver a entidade

        if(eventDelete.isEmpty()){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found"); 
        } 

        
        eventRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted Successfully"); 
    }


    public ResponseEntity<?> getEvent(Long id){
        var eventFound = eventRepository.findById(id);
        if(eventFound.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Event with the id %d not found", id));

        }
        return ResponseEntity.status(HttpStatus.OK).body(eventFound);
    }
}
