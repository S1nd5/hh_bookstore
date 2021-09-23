package com.example.bookstore.web;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;

import com.example.bookstore.domain.Book;

@RestController
public class RestControl {
	
	 @Autowired
	 private BookRepository repository;
	 
	// REST API Requests start
	
	@RequestMapping(value="/api/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookList() {
		return (List<Book>) repository.findAll();
	}
	
	@RequestMapping(value="/api/books/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
		return this.repository.findById(bookId);
	}
	
	@RequestMapping(value="/api/books/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(code = HttpStatus.OK, reason = "OK")
	public void deleteBookRest(@PathVariable("id") Long bookId) {
		this.repository.deleteById(bookId);
	}
	
	@RequestMapping(value="/api/books", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(code = HttpStatus.OK, reason = "OK")
	public void addBookRest(@RequestBody Book book) {
		this.repository.save(book);
	}
	
	@RequestMapping(value="/api/books/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	@ResponseStatus(code = HttpStatus.OK, reason = "OK")
	public Book updateBookRest(@RequestBody Book nBook, @PathVariable Long id) {
	    return repository.findById(id)
	    .map(book -> {
	    	 book.setTitle(nBook.getTitle());
	    	 book.setPrice(nBook.getPrice());
	    	 return repository.save(book);
	    })
	    .orElseGet(() -> {
		    nBook.setId(id);
		    return repository.save(nBook);
	    });
	}
	
	// REST API Requests end
}
