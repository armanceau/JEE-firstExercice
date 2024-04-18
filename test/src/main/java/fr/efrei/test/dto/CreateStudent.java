package fr.efrei.test.dto;

import jakarta.validation.constraints.*;


public class CreateStudent {

	@NotBlank
	@Size(min = 1, max = 25)
	private String name;

	@NotBlank
	private String firstname;

	public CreateStudent(String name, String firstname) {
		this.name = name;
		this.firstname = firstname;
	}

	public String getName() {
		return name;
	}

	public String getFirstname() {
		return firstname;
	}
}
