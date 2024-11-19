package com.hackon.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

public record EventDto(
    @Id @JsonProperty("id") Long id,
    @Nonnull @Column(length=200, nullable=false) String name,
    @Nonnull @Column(length=200, nullable=false) String description,
    @Nonnull  @Column(length=200, nullable=false) String location,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm") LocalDateTime startDateTime,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm") LocalDateTime endDateTime,
    @Id @JsonProperty("userId") Long userId
) {}
