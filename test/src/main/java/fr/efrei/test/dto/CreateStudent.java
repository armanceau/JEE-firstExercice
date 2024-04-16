package fr.efrei.test.dto;

public class CreateStudent {
    
    private String name;
    private String firstname;

    public CreateStudent(String name, String firstname){
        this.name = name;
        this.firstname  = firstname; 
    }

    public String getName() {
        return this.name;
    }

    public String getFirstname() {
        return this.firstname;
    }
}
