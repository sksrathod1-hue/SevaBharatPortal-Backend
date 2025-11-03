package com.sevabharat.portal.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles the root routing for the application by reading and serving the HTML file content directly.
 * This bypasses the static resource handler conflict that caused the 501 error.
 */
@RestController
public class HomeController {

    // Inject the static HTML file resource from the classpath (src/main/resources/static/homepage.html)
    @Value("classpath:static/homepage.html")
    private Resource homepage;

    /**
     * Maps the root URL (http://localhost:8090/) and serves the HTML content directly.
     * The method returns text/html content type.
     */
    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String serveHomePageDirectly() throws IOException {
        // Read the content of the HTML file resource into a String
        if (homepage.exists()) {
            return StreamUtils.copyToString(homepage.getInputStream(), StandardCharsets.UTF_8);
        } else {
            // Fallback error if the file doesn't exist (e.g., misspelled name)
            return "<html><body><h1>Error 404: Homepage file not found.</h1><p>Check if 'homepage.html' exists in src/main/resources/static/</p></body></html>";
        }
    }

    // Note: The /api/status endpoint is now in AuthController.java.
}