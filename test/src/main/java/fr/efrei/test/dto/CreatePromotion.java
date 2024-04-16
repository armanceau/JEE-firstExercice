package fr.efrei.test.dto;

import java.util.Set;

import fr.efrei.test.model.Student;

public class CreatePromotion {

    private String name;
    private Set<Student> students;

    public CreatePromotion(String name, Set<Student> students){
        this.name = name;
        this.students  = students; 
    }

    public String getName() {
        return this.name;
    }

    @SuppressWarnings("rawtypes")
    public Set getStudents() {
        return this.students;
    }
}