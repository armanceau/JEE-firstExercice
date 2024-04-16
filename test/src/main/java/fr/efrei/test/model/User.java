package fr.efrei.test.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue
    private String id;
    
    private String fullName;
    private String email;
    private String password;
}
