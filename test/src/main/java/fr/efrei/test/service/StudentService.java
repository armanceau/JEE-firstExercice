package fr.efrei.test.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.efrei.test.model.Student;
import fr.efrei.test.repository.StudentRepository;
import jakarta.transaction.Transactional;

@Service
public class StudentService {
    private final StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository){
        this.repository = repository;
    }

    public Student findStudentById(String uuid){
        System.out.println("je suis appelé (trouve un seul étudiant)");
        return repository.findOneByUuid(uuid).orElse(null);
    }

    public List<Student> findAllStudents(){
        System.out.println("je suis appelé (trouve tous les étudiants)");
        return repository.findAllByDeletedAtNull();
    }

    public Student create(Student student){
        System.out.println("je suis appelé (création étudiant)");
        return repository.save(student);
    }

    @Transactional
    public boolean delete(String uuid){
        Student studentASupprimer = findStudentById(uuid);
        if(studentASupprimer != null && studentASupprimer.getDeletedAt() != null){
            studentASupprimer.setDeletedAt(LocalDateTime.now());
            repository.save(studentASupprimer);
            return true;
        }
        return false;
    }
}
