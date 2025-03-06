package com.netflix.lolomodemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ArtworkService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ArtworkService.class);
    public String generateForTitle(String title) {
        LOGGER.info("Generating for {}", title);

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return UUID.randomUUID() + "-" + title.toLowerCase().replaceAll(" ", "-");
    }
}
