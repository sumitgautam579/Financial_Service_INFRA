package com.financial.service;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AnalyticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnalyticsApplication.class, args);
    }

    // Health endpoint for Kubernetes/readiness probes
    @GetMapping("/health")
    public String health() {
        return "OK";
    }

    // Example financial-services endpoint
    @GetMapping("/analytics/dummy")
    public String dummy() {
        // In real life, you'd call your service layer here
        return "{\"status\":\"stable\",\"timestamp\":\"" + System.currentTimeMillis() + "\"}";
    }
}
