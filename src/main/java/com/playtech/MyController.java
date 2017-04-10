package com.playtech;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
	
	private int timesVisited = 0;

	@RequestMapping("/")
	public @ResponseBody String handleRoot() {
		return "this is the root page of my server";
	}
	
	@RequestMapping("/add")
	public @ResponseBody String handleAdd() {
		return "this is the ADD page of my server " + ++timesVisited ;
	}
	
}
