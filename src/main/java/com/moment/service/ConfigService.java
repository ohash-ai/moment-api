package com.moment.service;

import com.moment.dto.ConfigResponse;
import com.moment.repository.AppConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfigService {

    private final AppConfigRepository appConfigRepository;

    public ConfigResponse getConfig() {
        String bottomQuote = appConfigRepository.findById("bottomQuote")
                .map(c -> c.getConfigValue())
                .orElse("");
        return new ConfigResponse(bottomQuote);
    }
}
