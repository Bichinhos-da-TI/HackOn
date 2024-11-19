package com.hackon.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackon.services.HackatonService;

@RestController
@RequestMapping("/hackathons")
public class HackathonController {
    private final HackatonService hackatonService;

    public HackathonController(HackatonService hackatonService) {
        this.hackatonService = hackatonService;
    }


}
