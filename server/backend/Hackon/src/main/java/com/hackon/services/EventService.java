package com.hackon.services;

import java.time.Duration;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import com.hackon.dto.EventDto;
import com.hackon.dto.UpdateEventDto;
import com.hackon.dto.request.CreateEventDto;
import com.hackon.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
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
    @Autowired
    private ModelMapper modelMapper;

    public List<EventDto> findAll(){
        return eventRepository.findAllEvents().stream().map(event -> modelMapper.map(event, EventDto.class)).collect(Collectors.toList());
    }

    public EventDto create(CreateEventDto eventDto) {
        if(!eventDto.getStartDateTime().isBefore(eventDto.getEndDateTime())){
            throw new IllegalArgumentException("Event Start date must be before End date");
        }

        if(!eventRepository.findEventsBetween(eventDto.getStartDateTime(), eventDto.getEndDateTime(), eventDto.getLocation()).isEmpty()){
            throw new RuntimeException("Event already exists at this location and this time");
        }
        if(Duration.between(eventDto.getStartDateTime(), eventDto.getEndDateTime()).toHours() < 1 || Duration.between(eventDto.getStartDateTime(), eventDto.getEndDateTime()).toHours() > 48){
            throw new RuntimeException("The minimum event duration is 1 hour, and the maximum event duration is 48 hours");
        }
        if(eventRepository.findAllByUserIdAndMonth(eventDto.getUserId(), eventDto.getStartDateTime().getMonth().getValue(), eventDto.getStartDateTime().getYear()).size() >= 5){
            throw new RuntimeException("You have already created 5 events this mounth");
        }

        Event event = modelMapper.map(eventDto, Event.class);
        eventRepository.save(event);
        return modelMapper.map(event, EventDto.class);
    }


    public void delete(Long id) throws NotFoundException {
        var eventDelete = eventRepository.findById(id);

        //#TODO tem que fazer a validação de autorizacao quando tiver a entidade de usuarios
        //#TODO verificacao de desafio em_andamento ou concluido quando tiver a entidade

        if(eventRepository.existsById(id)){
            throw new NotFoundException("event not found");
        }

        eventRepository.deleteById(id);
    }


    public EventDto getEvent(Long id) throws NotFoundException {
        var eventFound = eventRepository.findById(id).orElseThrow(() -> new NotFoundException("Event not found"));
        return modelMapper.map(eventFound, EventDto.class);
    }

    public void update(long id, UpdateEventDto dto){
        Event eventUpdate = eventRepository
                .findById(id)
                .orElseThrow();

        validateRequiredFields(dto);

        if(validateDate(dto.getStartDateTime(), dto.getEndDateTime())){
            eventUpdate.setStartDateTime(dto.getStartDateTime());
            eventUpdate.setEndDateTime(dto.getEndDateTime());
        } else {
            throw new RuntimeException("End date must be later than start date.");
        }

        eventUpdate.setName(dto.getName());
        eventUpdate.setDescription(dto.getDescription());

        eventRepository.save(eventUpdate);


    }

    private void validateRequiredFields(UpdateEventDto dto) {
        if (dto.getName().isEmpty()) {
            throw new IllegalArgumentException("Event title is required.");
        }
        if (dto.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Event description is required.");
        }
        if (dto.getStartDateTime() == null) {
            throw new IllegalArgumentException("Start date is required.");
        }
        if (dto.getEndDateTime() == null) {
            throw new IllegalArgumentException("End date is required.");
        }
    }

    private boolean validateDate(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return startDateTime != null && endDateTime != null && !startDateTime.isAfter(endDateTime);
    }
}
