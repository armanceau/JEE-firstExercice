package fr.efrei.test.controller;

import fr.efrei.test.dto.CreateStudent;
import fr.efrei.test.dto.UpdateStudent;
import fr.efrei.test.model.Student;
import fr.efrei.test.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/students")
@EnableMethodSecurity
public class StudentController {

	private final StudentService service;

	@Autowired
	public StudentController(StudentService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<Student>> findAll() {
		return new ResponseEntity<>(service.findAllStudents(), HttpStatus.OK);
	}

	@GetMapping("/{uuid}")
	public ResponseEntity<Student> findOneById(@PathVariable String uuid) {
		Student student = service.findStudentById(uuid);
		if(student != null) {
			return new ResponseEntity<>(service.findStudentById(uuid), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping
	public ResponseEntity<Student> save(@Valid @RequestBody CreateStudent student) {
		Student createdStudent = service.create(student);
		return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
	}

	@DeleteMapping("/{uuid}")
	public ResponseEntity<?> delete(@PathVariable String uuid) {
		boolean isDeleted = service.delete(uuid);
		if(isDeleted) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/{uuid}")
	public ResponseEntity<?> mettreAJourTotalement(
			@PathVariable String uuid,
			@RequestBody UpdateStudent student) {
		boolean isUpdated = service.update(uuid, student);
		if(isUpdated) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PatchMapping("/{uuid}")
	public ResponseEntity<?> mettreAjourPartiellement(
			@PathVariable String uuid,
			@RequestBody UpdateStudent student) {
		boolean isUpdated = service.updatePartielle(uuid, student);
		if(isUpdated) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
