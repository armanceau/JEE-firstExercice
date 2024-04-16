package fr.efrei.test.dto;

public class UpdateStudent {
    private String name;
    private String firstname;

    public UpdateStudent(String name, String firstname){
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
