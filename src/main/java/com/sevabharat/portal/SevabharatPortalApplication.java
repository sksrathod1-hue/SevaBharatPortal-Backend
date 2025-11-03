package com.sevabharat.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc; // For explicit web configuration

/**
 * Main Spring Boot application class.
 */
@SpringBootApplication
// @EnableWebMvc is often redundant but helps ensure web configuration is fully loaded after classpath issues
@EnableWebMvc 
public class SevabharatPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(SevabharatPortalApplication.class, args);
    }

    // NOTE: Because we are using @EnableWebMvc, Spring is forced to load MVC components, 
    // including the static resource handler, which should resolve the 501 error 
    // once the AuthenticationManager crash is fixed.
}