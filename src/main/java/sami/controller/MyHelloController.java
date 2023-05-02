package sami.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Welcome")
public class MyHelloController {
	
	@GetMapping(path="/helloWorld")
	public String HelloWorld() {
		
		System.out.println("MyHelloController.helloWorld()");
		
		return "Hello world in english is returned";
		
	}
	
	@GetMapping(path="/howAreYou")
	public String howAreYou() {
		
		System.out.println("MyHelloController.howAreYou()");
		
		return "How are you english is returned";
		
	}
}
