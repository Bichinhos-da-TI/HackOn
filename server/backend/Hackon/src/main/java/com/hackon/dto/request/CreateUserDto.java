package com.hackon.dto.request;

import com.hackon.dto.enums.UserRoleEnum;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;

public record CreateUserDto(
    @Nonnull @Column(length=200, nullable=false) String name,
    @Nonnull @Column(length=200, nullable=false) String email,
    @Nonnull @Column(length=200, nullable=false) String password,
    @Nonnull UserRoleEnum role
) {}
