package com.salesianostriana.dam.usuario;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserDataSeed {
	
	private final UserRepository repo;
	private final PasswordEncoder encoder;
	
	@PostConstruct
	public void init() {
		
		User user = User.builder()
				.email("user@user.com")
				.username("user")
				.password(encoder.encode("user"))
				.role(UserRole.USER)
				.build();
		
		repo.save(user);
		
		
		User admin = User.builder()
				.email("admin@admin.com")
				.username("admin")
				.password(encoder.encode("admin"))
				.role(UserRole.ADMIN)
				.build();
		
		repo.save(admin);
		
	}
	

}
