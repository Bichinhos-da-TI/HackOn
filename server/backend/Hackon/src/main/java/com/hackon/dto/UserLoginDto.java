package com.hackon.dto;

public record UserLoginDto (
    String password,
    String username,
    String clientId,
    String grantType
){

}
