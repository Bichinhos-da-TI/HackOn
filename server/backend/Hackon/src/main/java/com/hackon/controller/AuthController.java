package com.hackon.controller;

import com.hackon.dto.UserLoginDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;

@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {

    @PostMapping("")
    public ResponseEntity<String> getToken(@RequestBody UserLoginDto userLoginDto,
                                           @Value("${keycloak.realm}") String realm,
                                           @Value("${keycloak.client-id}") String clientId) {
        HttpHeaders headers = new HttpHeaders();
        RestTemplate rt = new RestTemplate();
        headers.setContentType(APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", clientId);
        formData.add("grant_type", userLoginDto.grantType());
        formData.add("username", userLoginDto.username());
        formData.add("password", userLoginDto.password());

        HttpEntity<MultiValueMap<String, String>> entity =
                new HttpEntity<MultiValueMap<String, String>>(formData, headers);


        var res = rt.postForEntity("http://localhost:8081/realms/" + realm +"/protocol/openid-connect/token" ,entity, String.class);
        return res;
    }


}
