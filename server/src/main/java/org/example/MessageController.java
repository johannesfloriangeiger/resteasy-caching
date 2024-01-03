package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.logging.Logger;

@RestController
public class MessageController {

    private static final Logger LOGGER = Logger.getLogger(MessageController.class.getName());

    private final String cacheControl;

    public MessageController(@Value("${org.example.cache-expiration:PT10S}") final Duration maxAge) {
        this.cacheControl = CacheControl.maxAge(maxAge)
                .getHeaderValue();
    }

    @GetMapping("api/message")
    public ResponseEntity<String> read() {
        LOGGER.info("Request received");

        return ResponseEntity.ok()
                .header(HttpHeaders.CACHE_CONTROL, this.cacheControl)
                .body("Hello World");
    }
}
