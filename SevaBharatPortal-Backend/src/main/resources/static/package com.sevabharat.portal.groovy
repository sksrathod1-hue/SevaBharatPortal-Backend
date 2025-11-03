package com.sevabharat.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Handles the root routing for the application.
 */
@Controller // Use @Controller to serve views (ensures index.html loads)
public class HomeController {

    /**
     * Maps the root URL (http://localhost:8090/) to the static index HTML page.
     * This is required for the browser to find your main website file.
     * @return the view name "index", which Spring resolves to "src/main/resources/static/index.html"
     */
    @GetMapping("/")
    public String serveIndexView() {
        return "index"; 
    }

    // REMOVED: The /api/status endpoint was moved to AuthController to avoid conflict.
}
