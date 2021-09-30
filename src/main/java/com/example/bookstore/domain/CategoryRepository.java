package com.example.bookstore.domain;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	List<Category> findByid(Long id);
	
	@RestResource(path="findByName", rel="findByName")
	List<Category> findByNameContainingIgnoreCase(@Param("name") String name);
}
