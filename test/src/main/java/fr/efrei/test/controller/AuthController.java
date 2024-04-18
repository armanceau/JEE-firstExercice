package fr.efrei.test.controller;

import fr.efrei.test.dto.LoginDto;
import fr.efrei.test.dto.LoginResponse;
import fr.efrei.test.dto.RegisterDto;
import fr.efrei.test.model.User;
import fr.efrei.test.service.AuthService;
import fr.efrei.test.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthController {
	private final JwtService jwtService;

	private final AuthService authenticationService;

	public AuthController(JwtService jwtService, AuthService authenticationService) {
		this.jwtService = jwtService;
		this.authenticationService = authenticationService;
	}

	@PostMapping("/signup")
	public ResponseEntity<User> register(@Valid @RequestBody RegisterDto registerUserDto) {
		User registeredUser = authenticationService.signup(registerUserDto);
		return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> authenticate(
			@RequestBody LoginDto loginUserDto) {
		User authenticatedUser = authenticationService.authenticate(loginUserDto);

		String jwtToken = jwtService.generateToken(authenticatedUser);

		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setToken(jwtToken);
		loginResponse.setExpiresIn(jwtService.getExpirationTime());

		return new ResponseEntity<>(loginResponse, HttpStatus.OK);
	}
}
