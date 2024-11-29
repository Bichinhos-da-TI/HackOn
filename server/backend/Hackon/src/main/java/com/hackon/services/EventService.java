package com.hackon.services;

import java.time.Duration;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Optional;


import com.hackon.dto.UpdateEventDto;
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

    public ArrayList<Event> findAll(){
        return (ArrayList<Event>) eventRepository.findAllEvents();
    }

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

    public ResponseEntity<Void> update(long id, UpdateEventDto dto){
        Event eventUpdate = eventRepository
                .findById(id)
                .orElseThrow();

        validateRequiredFields(dto);

        if(validateDate(dto.startDateTime(), dto.endDateTime())){
            eventUpdate.setStartDateTime(dto.startDateTime());
            eventUpdate.setEndDateTime(dto.endDateTime());
        } else {
            throw new RuntimeException("End date must be later than start date.");
        }

        eventUpdate.setName(dto.name());
        eventUpdate.setDescription(dto.description());

        eventRepository.save(eventUpdate);

        return ResponseEntity.ok().build();
    }

    private void validateRequiredFields(UpdateEventDto dto) {
        if (dto.name().isEmpty()) {
            throw new IllegalArgumentException("Event title is required.");
        }
        if (dto.description().isEmpty()) {
            throw new IllegalArgumentException("Event description is required.");
        }
        if (dto.startDateTime() == null) {
            throw new IllegalArgumentException("Start date is required.");
        }
        if (dto.endDateTime() == null) {
            throw new IllegalArgumentException("End date is required.");
        }
    }

    private boolean validateDate(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return startDateTime != null && endDateTime != null && !startDateTime.isAfter(endDateTime);
    }
}
