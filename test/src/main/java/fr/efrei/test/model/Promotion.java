package fr.efrei.test.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Promotion {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String uuid;

	private String name;

	@OneToMany
	private Set<Student> students = new HashSet<>();

	public String getName() {
		return name;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public String getUuid() {
		return uuid;
	}
}
