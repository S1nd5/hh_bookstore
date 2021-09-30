package com.example.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

import com.example.bookstore.domain.RegisterForm;
import com.example.bookstore.domain.User;
import com.example.bookstore.domain.UserRepository;

@Controller
public class UserController {
	@Autowired
    private UserRepository repository;
	
	// Endpoints
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	
	
    @RequestMapping(value = "/signup")
    public String addStudent(Model model){
    	model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }	
    
    // User creation, form validation
    @RequestMapping(value = "saveuser", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("registerForm") RegisterForm registerForm, BindingResult bindingResult) {
    	if (!bindingResult.hasErrors()) { // validation errors
    		if (registerForm.getPassword().equals(registerForm.getPasswordCheck())) { // check password match		
	    		String pwd = registerForm.getPassword();
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	String hashPwd = bc.encode(pwd);
	
		    	User newUser = new User();
		    	newUser.setPasswordHash(hashPwd);
		    	newUser.setUsername(registerForm.getUsername());
		    	newUser.setRole("USER");
		    	if (repository.findByUsername(registerForm.getUsername()) == null) { // Check if user exists
		    		repository.save(newUser);
		    	}
		    	else {
	    			bindingResult.rejectValue("username", "err.username", "Username already exists");    	
	    			return "register";		    		
		    	}
    		}
    		else {
    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");    	
    			return "register";
    		}
    	}
    	else {
    		return "register";
    	}
    	return "redirect:/login";    	
    }    
    
}