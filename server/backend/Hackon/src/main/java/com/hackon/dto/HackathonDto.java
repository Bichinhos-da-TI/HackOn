package com.hackon.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hackon.dto.enums.HackathonEnumStatus;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

public record HackathonDto(
    @Id @JsonProperty("id") Long id,
    @Nonnull @Column(length=200, nullable=false) String name,
    @Nonnull HackathonEnumStatus status,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm") LocalDateTime date,
    @Nonnull @Column(length=200, nullable=false) String location
) {}
