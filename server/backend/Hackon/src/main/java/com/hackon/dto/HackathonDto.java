package com.hackon.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hackon.dto.enums.HackathonEnumStatus;

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
public class HackathonDto {
    @NotNull(message = "Id cannot be null")
    @JsonProperty("id")
    Long id;

    @NotNull(message = "name cannot be null")
    @Size(min = 1, max = 200, message = "Name must be between 1 and 200 characters")
    String name;

    @NotNull(message = "status cannot be null")
    @Size(min = 1, max = 200, message = "Status must be between 1 and 200 characters")
    HackathonEnumStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    LocalDateTime date;

    @NotNull(message = "Location cannot be null")
    @Size(min = 1, max = 200, message = "Location must be between 1 and 200 characters")
    String location;
}
