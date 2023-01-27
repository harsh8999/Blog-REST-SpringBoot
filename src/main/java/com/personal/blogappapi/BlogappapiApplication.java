package com.personal.blogappapi;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.personal.blogappapi.config.AppConstants;
import com.personal.blogappapi.entities.Role;
import com.personal.blogappapi.repository.RoleRepo;

@SpringBootApplication
public class BlogappapiApplication implements CommandLineRunner{

	@Autowired
	private PasswordEncoder passwordEncoder;


	// create the if roles are not present 
	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogappapiApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("123456789"));
		try {
			Role roleAdmin = new Role();
			roleAdmin.setId(AppConstants.ADMIN_USER);
			roleAdmin.setName("ROLE_ADMIN");

			Role roleNormal = new Role();
			roleNormal.setId(AppConstants.NORMAL_USER);
			roleNormal.setName("ROLE_NORMAL");

			List<Role> roles = List.of(roleAdmin, roleNormal);

			List<Role> result = this.roleRepo.saveAll(roles);

			result.forEach(role -> System.out.println(role.getName()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
}
