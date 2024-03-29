package fr.efrei.test.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import fr.efrei.test.model.Student;

public interface StudentRepository extends JpaRepository<Student, String> {
    
    List<Student> findAllByDeletedAtNull();

    Optional<Student> findOneByUuid(String uuid);

    Student save(Student student);

    void deleteByUuid(String uuid);

    //Exemple faire un requete sql : 
    // @Query("Select uuid from Student")
    // List<Student> findAllSQL();
}
