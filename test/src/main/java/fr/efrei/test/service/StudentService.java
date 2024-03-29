package fr.efrei.test.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.efrei.test.dto.CreateStudent;
import fr.efrei.test.dto.UpdateStudent;
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

    public Student create(CreateStudent student){
        System.out.println("je suis appelé (création étudiant)");
        Student studentACreer = new Student(student.getName(), student.getFirstname());
        // studentACreer.setName(student.getName());
        // studentACreer.setFirstname(student.getFirstname());
        return repository.save(studentACreer);
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

    public boolean update(String uuid, UpdateStudent student){
        Student studentAModifier = findStudentById(uuid);
        if(studentAModifier != null){
            studentAModifier.setFirstname(student.getFirstname());
            studentAModifier.setName(student.getName());   
            repository.save(studentAModifier);
            return true;
        }
        return false;
    }

    public boolean updatePartielle(String uuid, UpdateStudent student){
        Student studentAModifier = findStudentById(uuid);
        if(studentAModifier != null){
            if(!student.getFirstname().isEmpty()){
                studentAModifier.setFirstname(student.getFirstname());
            }
            if(!student.getName().isEmpty()){
                studentAModifier.setName(student.getName());
            }
            return true;
        }
        return false;
    }

    
}
