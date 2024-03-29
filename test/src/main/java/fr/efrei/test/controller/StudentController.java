package fr.efrei.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import fr.efrei.test.model.Student;
import fr.efrei.test.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    @Autowired
    public StudentController(StudentService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Student>> findAll(){
        return new ResponseEntity<>(service.findAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Student> findOneById(@PathVariable String uuid){
        Student student = service.findStudentById(uuid);
        if(student != null){
            return new ResponseEntity<>(service.findStudentById(uuid), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Student> save(@RequestBody Student student){
        Student createdStudent = service.create(student);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete(@PathVariable String uuid){
        boolean isDeleted = service.delete(uuid);
        if(isDeleted){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);       
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
