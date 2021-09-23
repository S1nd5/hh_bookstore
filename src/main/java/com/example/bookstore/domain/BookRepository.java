package com.example.bookstore.domain;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
	List<Book> findByid(Long id);
	
	@RestResource(path="findByTitle", rel="findByTitle")
	List<Book> findByTitleContainingIgnoreCase(@Param("title") String title);
	
	@RestResource(path="findByAuthor", rel="findByAuthor")
	List<Book> findByAuthorContainingIgnoreCase(@Param("author") String author);
	
	@RestResource(path="findByIsbn", rel="findByIsbn")
	List<Book> findByIsbnContainingIgnoreCase(@Param("isbn") String isbn);
	
	@RestResource(path="findByYear", rel="findByYear")
	List<Book> findByYear(@Param("year") String year);
}
