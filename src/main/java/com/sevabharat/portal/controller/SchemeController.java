package com.sevabharat.portal.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Add this annotation to allow requests from your front-end host and port
@CrossOrigin(origins = "http://localhost:8081") 
@RestController
@RequestMapping("/api/schemes")
public class SchemeController {

    // ... your methods
}