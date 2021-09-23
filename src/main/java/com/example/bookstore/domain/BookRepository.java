package com.example.bookstore.domain;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
	List<Book> findByid(Long id);
	List<Book> findByTitle(@Param("title") String title);
	List<Book> findByAuthor(@Param("author") String author);
	List<Book> findByIsbn(@Param("isbn") String isbn);
	List<Book> findByYear(@Param("year") String year);
}
