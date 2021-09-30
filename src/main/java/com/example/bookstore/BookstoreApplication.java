package com.example.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;
import com.example.bookstore.domain.User;
import com.example.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	
	@Autowired
	private BookRepository repository;
	 
	@Autowired
	private CategoryRepository catRepository;
	
	@SuppressWarnings("unused")
	@Autowired
	private UserRepository usrRepository;

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository catRepository, UserRepository usrRepository) {
		return (args) -> {
		 // Your code...add some demo data to db
			Category kategoria = new Category("Yleinen");
			this.catRepository.save(kategoria);
			this.catRepository.save(new Category("Turhakkeet"));
			Category illu = new Category("Illuminati");
			this.catRepository.save(illu);
			//String title, String author, int year, String isbn, Double price
			Book uusi = new Book("Testikirja 1", "Pirkko Puro", "2010", "AAAA", 19.95, kategoria);
			Book uusi2 = new Book("Testikirja 2", "Matti Puro", "2020", "BBBB", 19.95, kategoria);
			this.repository.save(uusi);
			this.repository.save(uusi2);
			this.repository.save(new Book("WTC-tornien kohtalo", "Anonymous", "2021", "DDDD", 99.95, illu));
			
			//User data
			User user1 = new User("user", "$2a$12$LIn5TqFL5tpNkiyMUjiyWeubLXIpc3S87lj/Q8HcYaeF77cNULVIa", "USER");
			User user2 = new User("admin", "$2a$12$5nDhI9lcAjEdlGD4P40.jerKOXuGbWfn/HFIawRuO/yzYKU54y2Ty", "ADMIN");
			usrRepository.save(user1);
			usrRepository.save(user2);
		};
	}
}
