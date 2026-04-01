package com.moment.controller;

import com.moment.dto.ConfigResponse;
import com.moment.service.ConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ConfigController {

    private final ConfigService configService;

    @GetMapping("/config")
    public ConfigResponse getConfig() {
        return configService.getConfig();
    }
}
