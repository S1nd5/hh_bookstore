package com.example.bookstore.web;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	
	 @Autowired
	 private BookRepository repository;
	 
	 @Autowired
	 private CategoryRepository catRepository;
	 
	 public void doSomething() {
		 //List<Book> books = repository.getIsbn("AAAA");
	 }
	 
	@RequestMapping("/booklist")
	public String home(Model model) {
		List<Book> books = (List<Book>) this.repository.findAll();
		System.out.println(books);
		model.addAttribute("books", books);
		return "booklist";
	}
	
	@RequestMapping("/addbook")
	public String addbook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", catRepository.findAll());
		return "addbook";
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String removeBook(@PathVariable Long id) {
		this.repository.deleteById(id);
		System.out.println("Removed book: " + id);
		return "redirect:/booklist"; 
	}
	
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	public String saveBook(@ModelAttribute Book book, Model model) {
		System.out.println("### SAVE");
		System.out.println(book);
		this.repository.save(book);
		return "redirect:/booklist"; 
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String editBook(@PathVariable Long id, Model model) {
		model.addAttribute("book", this.repository.findById(id));
		model.addAttribute("categories", catRepository.findAll());
		return "editbook";
	}
	
	@PostMapping("/addbook")
	public String bookSubmit(@ModelAttribute Book book, Model model) {
		if ( book.getAuthor().length() > 3 && book.getIsbn().length() >= 10 && book.getTitle().length() > 3 ) {
			this.repository.save(book);
			return "redirect:/booklist";
		} else {
			model.addAttribute("msg", "Invalid input, make sure that you have provided details asked.");
			return "addbook";
		}
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository repository) {
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
		};
	}
	
	@RequestMapping("/")
	public String direct() {
		return "redirect:/booklist";
	}
}
