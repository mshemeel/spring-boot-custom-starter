package com.example.greeting.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for the Greeting starter.
 */
@ConfigurationProperties(prefix = "greeting")
public class GreetingProperties {

    /**
     * The greeting message to be displayed.
     */
    private String message = "Hello";

    /**
     * Whether to include the current time in the greeting.
     */
    private boolean includeTime = false;
    
    /**
     * API key for greeting service (if required).
     */
    private String apiKey;
    
    /**
     * Custom message for authenticated greetings.
     */
    private String customMessage;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isIncludeTime() {
        return includeTime;
    }

    public void setIncludeTime(boolean includeTime) {
        this.includeTime = includeTime;
    }
    
    public String getApiKey() {
        return apiKey;
    }
    
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    
    public String getCustomMessage() {
        return customMessage;
    }
    
    public void setCustomMessage(String customMessage) {
        this.customMessage = customMessage;
    }
} 