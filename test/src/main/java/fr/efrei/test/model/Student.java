package fr.efrei.test.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    private String name;

    private String firstname;

    private LocalDateTime deletedAt = null;

    public Student(){}

    public Student(String name, String firstname){
        this.name = name;
        this.firstname = firstname;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for firstname
    public String getFirstname() {
        return firstname;
    }

    // Setter for firstname
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getUuid(){
        return uuid;
    }

    public void setDeletedAt(LocalDateTime deletedAt){
        this.deletedAt = deletedAt;
    }

    public LocalDateTime getDeletedAt(){
        return deletedAt;
    }
}
