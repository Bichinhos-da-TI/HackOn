package com.hackon.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
        @NotNull(message = "Id cannot be null")
        @JsonProperty("id")
        Long id;

        @NotNull(message = "Name cannot be null")
        @Size(min = 1, max = 200, message = "Name must be between 1 and 200 characters")
        String name;

        @NotNull(message = "Description cannot be null")
        @Size(min = 1, max = 200, message = "Description must be between 1 and 200 characters")
        String description;

        @NotNull(message = "Location cannot be null")
        @Size(min = 1, max = 200, message = "Location must be between 1 and 200 characters")
        String location;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime startDateTime;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime endDateTime;

        @NotNull(message = "userId cannot be null")
        @JsonProperty("userId")
        Long userId;
}
