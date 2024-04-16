package fr.efrei.test.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    private String name;

    @OneToMany(cascade = CascadeType.REMOVE)
    //@JoinColumn(name = "promotion_id")
    private Set<Student> students = new HashSet();

    private LocalDateTime deletedAt = null;

    public Promotion(){}

    @SuppressWarnings("unchecked")
    public Promotion(String name, Set students) {
        this.name = name;
        this.students = students;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudents() {
        return this.students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public void setDeletedAt(LocalDateTime deletedAt){
        this.deletedAt = deletedAt;
    }

    public LocalDateTime getDeletedAt(){
        return deletedAt;
    }

    // students.add(nouvelEtudiant);

}
