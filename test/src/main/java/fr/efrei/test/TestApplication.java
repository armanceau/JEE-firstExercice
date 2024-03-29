package fr.efrei.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.efrei.test.model.Student;


@SpringBootApplication
@RestController
@RequestMapping("/")
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	@GetMapping("hello-world")
	public Student helloWorld(){
		Student student = new Student("Manceau", "Arthur");
		return student;
	} 

	@GetMapping("test-{toto}")
	public String test(@PathVariable(name = "toto") String toto){
		return "cc " + toto;
	} 

}
