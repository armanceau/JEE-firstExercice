package fr.efrei.test.repository;

import fr.efrei.test.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

	// SELECT * from Student where deleted_at is null
	List<Student> findAllByDeletedAtNull();

	// SELECT * FROM Student where uuid = ?
	Optional<Student> findOneByUuid(String uuid);

	Student save(Student student);

	void deleteByUuid(String uuid);
}
