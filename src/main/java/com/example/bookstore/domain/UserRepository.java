package com.example.bookstore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

@SuppressWarnings("unused")
public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}