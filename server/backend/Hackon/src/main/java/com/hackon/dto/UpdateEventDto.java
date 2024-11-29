package com.hackon.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

public record UpdateEventDto (
        @Nonnull @Column(length=200, nullable=false) String name,
        @Nonnull @Column(length=200, nullable=false) String description,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm") LocalDateTime startDateTime,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm") LocalDateTime endDateTime
){
}
