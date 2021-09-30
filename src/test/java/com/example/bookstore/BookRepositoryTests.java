package com.example.bookstore;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.Category;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTests {
	
	@Autowired
	private BookRepository repository;
	
	@Test
	 public void findAllShouldReturnBooks() {
	 List<Book> books = (List<Book>) repository.findAll();
	 assertThat(books).hasSize(3);
	}
	
	@Test
	 public void findByTitleShouldReturnBooks() {
	 List<Book> books = repository.findByTitleContainingIgnoreCase("Testi");
	 assertThat(books).hasSize(2);
	 assertThat(books.get(0).getIsbn().contentEquals("AAAA"));
	}
	
	@Test
	public void findByAuthorShouldReturnBooks() {
		 List<Book> books = repository.findByAuthorContainingIgnoreCase("Puro");
		 assertThat(books).hasSize(2);
		 assertThat(books.get(0).getAuthor().contains("Pirkko"));
		 assertThat(books.get(1).getAuthor().contains("Matti"));
	}
	
	@Test
	public void findByYearShouldReturnBooks() {
		 List<Book> books = repository.findByYear("2020");
		 assertThat(books).hasSize(1);
		 assertThat(books.get(0).getAuthor().contains("Matti"));
	}
	
	@Test
	public void findByIsbnShouldReturnBooks() {
		 List<Book> books = repository.findByIsbnContainingIgnoreCase("DDDD");
		 assertThat(books).hasSize(1);
		 assertThat(books.get(0).getIsbn().contains("DDDD"));
	}
	
	@Test
	public void createNewBook() {
		Book book = new Book("Test Book", "System", "2021", "TEST-ISBN", 0.00, new Category("TESTING"));
		repository.save(book);
		assertThat(book.getId()).isNotNull();
	}
	
	@Test
	public void deleteABook() {
		Long id = (long) 1;
		repository.deleteById(id);
		assertThat(repository.findById(id)).isEmpty();
	}
}
