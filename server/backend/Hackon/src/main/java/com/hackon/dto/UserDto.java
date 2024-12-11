package com.hackon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hackon.dto.enums.UserRoleEnum;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
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
public class UserDto{
    @NotNull
    @JsonProperty("id")
    Long id;

    @NotNull
    @Size(min = 1, max = 200, message = "Username must be between 1 and 200 characters")
    String username;

    @NotNull
    @Size(min = 1, max = 200, message = "Password must be between 1 and 200 characters")
    String password;

    @NotNull
    UserRoleEnum role;
}
