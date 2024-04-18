package fr.efrei.test.service;

import fr.efrei.test.dto.CreateStudent;
import fr.efrei.test.dto.UpdateStudent;
import fr.efrei.test.model.Student;
import fr.efrei.test.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentService {

	private final StudentRepository repository;

	@Autowired
	public StudentService(StudentRepository repository) {
		this.repository = repository;
	}

	public List<Student> findAllStudents() {
		return repository.findAllByDeletedAtNull();
	}

	public Student findStudentById(String uuid) {
		return repository.findOneByUuid(uuid).orElse(null);
	}

	public Student create(CreateStudent student) {
		// ici je suis dans la DTO
		//
		Student studentACreer = new Student(
				student.getName(),
				student.getFirstname()
		);
		// je suis dans une entité
		return repository.save(studentACreer);
	}

	@Transactional
	public boolean delete(String uuid) {
		Student studentASupprimer = findStudentById(uuid);
		if(studentASupprimer != null && studentASupprimer.getDeletedAt() == null) {
			studentASupprimer.setDeletedAt(LocalDateTime.now());
			repository.save(studentASupprimer);
			return true;
		}
		return false;
	}

	public boolean update(String uuid, UpdateStudent student) {
		Student studentAModifier = findStudentById(uuid);

		if(studentAModifier != null) {
			studentAModifier.setFirstname(student.getFirstname());
			studentAModifier.setName(student.getName());
			repository.save(studentAModifier);
			return true;
		}
		return false;
	}

	public boolean updatePartielle(String uuid, UpdateStudent student) {
		Student studentAModifier = findStudentById(uuid);

		if(studentAModifier != null) {
			if(!student.getFirstname().isEmpty()) {
				studentAModifier.setFirstname(student.getFirstname());
			}
			if(!student.getName().isEmpty()) {
				studentAModifier.setName(student.getName());
			}
			repository.save(studentAModifier);
			return true;
		}
		return false;
	}
}
