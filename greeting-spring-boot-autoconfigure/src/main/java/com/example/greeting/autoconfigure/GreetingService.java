package com.example.greeting.autoconfigure;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Service to generate greeting messages.
 */
public class GreetingService {

    private final GreetingProperties properties;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public GreetingService(GreetingProperties properties) {
        this.properties = properties;
    }

    /**
     * Generate a greeting message for the given name.
     *
     * @param name the name to greet
     * @return a greeting message
     */
    public String greet(String name) {
        StringBuilder greeting = new StringBuilder(properties.getMessage() + ", " + name + "!");
        
        if (properties.isIncludeTime()) {
            greeting.append(" The current time is: ")
                    .append(LocalDateTime.now().format(formatter));
        }
        
        return greeting.toString();
    }
    
    /**
     * Generate an authenticated greeting using the API key.
     *
     * @param name the name to greet
     * @param apiKey the API key for authentication
     * @return an authenticated greeting or an error message
     */
    public String authenticatedGreet(String name, String apiKey) {
        // Verify the API key
        if (properties.getApiKey() != null && 
            properties.getApiKey().equals(apiKey)) {
            
            String message = properties.getCustomMessage();
            if (message == null || message.isEmpty()) {
                message = "Premium welcome";
            }
            
            StringBuilder greeting = new StringBuilder(message + ", " + name + "!");
            
            if (properties.isIncludeTime()) {
                greeting.append(" The current time is: ")
                        .append(LocalDateTime.now().format(formatter));
            }
            
            return greeting.toString();
        }
        
        return "Invalid API key. Access denied.";
    }
} 