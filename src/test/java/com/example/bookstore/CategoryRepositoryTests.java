package com.example.bookstore;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.bookstore.domain.CategoryRepository;
import com.example.bookstore.domain.Category;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CategoryRepositoryTests {
	
	@Autowired
	private CategoryRepository repository;
	
	@Test
	 public void findAllShouldReturnCategories() {
	 List<Category> categories = (List<Category>) repository.findAll();
	 assertThat(categories).hasSize(3);
	}
	
	@Test
	 public void findByNameReturnCategories() {
	 List<Category> categories = (List<Category>) repository.findByNameContainingIgnoreCase("Yleinen");
	 assertThat(categories).hasSize(1);
	 assertThat(categories.get(0).getName().contains("Yleinen"));
	}
	
	@Test
	 public void findByIdReturnCategories() {
	 Long id = (long) 1;
	 Optional<Category> categories = repository.findById(id);
	 assertThat(categories.get().getName().contains("Yleinen"));
	}
}
