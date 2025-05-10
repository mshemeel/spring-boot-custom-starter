package com.example.greetingsample;

import com.example.greeting.autoconfigure.GreetingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private final GreetingService greetingService;
    
    @Value("${GREETING_API_KEY:#{null}}")
    private String apiKey;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name) {
        return greetingService.greet(name);
    }
    
    @GetMapping("/secure-greet/{name}")
    public String secureGreet(
            @PathVariable String name,
            @RequestHeader(value = "API-Key", required = true) String providedApiKey) {
        return greetingService.authenticatedGreet(name, providedApiKey);
    }
} 