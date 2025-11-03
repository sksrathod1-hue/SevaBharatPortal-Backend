package com.sevabharat.portal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a user in the system, mapped to the 'users' table in MySQL.
 */
@Entity
@Table(name = "users") // Maps this class to the 'users' table
public class User {

    // Matches the 'id' column, set as the Primary Key and auto-incremented
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Matches the 'username' column
    @Column(unique = true, nullable = false)
    private String username;

    // Matches the 'password' column (will store the encoded password)
    @Column(nullable = false)
    private String password;

    // Matches the 'age' column
    private int age;
    
    // Matches the 'income' column
    private Long income; 

    // Matches the 'email' column in the DB. Some existing DBs may have this column set NOT NULL,
    // so include it here and allow server-side to populate a fallback value if missing.
    @Column
    private String email;
    
    // Store the full name of the user (optional). If your DB has a 'name' column, this will map to it.
    @Column
    private String name;
    // --- Constructors ---
    public User() {}

    // --- Getters and Setters (Required by JPA) ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getIncome() {
        return income;
    }

    public void setIncome(Long income) {
        this.income = income;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

    
