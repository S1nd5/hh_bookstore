package com.example.bookstore;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.*;

import com.example.bookstore.web.BookController;
import com.example.bookstore.web.RestControl;
import com.example.bookstore.web.UserController;
import com.example.bookstore.web.UserService;

@ExtendWith(SpringExtension.class) 
@SpringBootTest
class BookstoreApplicationTests {
	
	@Autowired
	private BookController bController;
	private RestControl rController;
	private UserController uController;
	private UserService uService;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(bController).getClass();
		assertThat(rController).getClass();
		assertThat(uController).getClass();
		assertThat(uService).getClass();
	}

}
