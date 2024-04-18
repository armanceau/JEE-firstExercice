package fr.efrei.test.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String uuid;

	@Column(
			length = 25
	)
	private String name;

	private String firstname;

	private LocalDateTime deletedAt = null;

	public Student() {}
	public Student(String name, String firstname) {
		this.name = name;
		this.firstname = firstname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getUuid() {
		return uuid;
	}

	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}
}
