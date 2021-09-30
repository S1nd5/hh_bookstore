package com.example.bookstore;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.bookstore.domain.User;
import com.example.bookstore.domain.UserRepository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTests {
	@Autowired
	private UserRepository repository;
	
	@Test
	 public void findAllShouldReturnUsers() {
	 List<User> users = (List<User>) repository.findAll();
	 assertThat(users).hasSize(2);
	}
	
	@Test
	 public void findByUsernameShouldReturnUser() {
	 User user = repository.findByUsername("user");
	 assertThat(user.getUsername().contains("user"));
	}
}
