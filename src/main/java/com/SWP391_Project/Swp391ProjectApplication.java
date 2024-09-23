package com.SWP391_Project;

import com.SWP391_Project.Entity.User;
import com.SWP391_Project.Enum.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Swp391ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(Swp391ProjectApplication.class, args);

	}


}
