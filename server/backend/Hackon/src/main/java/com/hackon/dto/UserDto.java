package com.hackon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hackon.dto.enums.UserRoleEnum;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

public record UserDto(
    @Id @JsonProperty("id") Long id,
    @Nonnull @Column(length=200, nullable=false) String name,
    @Nonnull @Column(length=200, nullable=false) String email,
    @Nonnull @Column(length=200, nullable=false) String password,
    @Nonnull UserRoleEnum role
) {}
