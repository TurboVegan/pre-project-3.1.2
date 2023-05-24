package com.example.SpringBootApp1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.example.SpringBootApp1.models.User;
import com.example.SpringBootApp1.services.UsersService;

@Controller
@RequestMapping("/users")
public class UsersController {

	private final UsersService usersService;

	@Autowired
	public UsersController(UsersService usersService) {
		this.usersService = usersService;
	}

	@GetMapping()
	public String index(ModelMap model) {
		model.addAttribute("users", usersService.findAll());
		return "users/index";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		model.addAttribute("user", usersService.findOne(id));
		return "users/show";
	}

	@GetMapping("/new")
	public String newUser(@ModelAttribute("user") User user) {
		return "users/new";
	}

	@PostMapping()
	public String create(@ModelAttribute("user") User user) {
		usersService.save(user);
		return "redirect:/users";
	}

	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("user", usersService.findOne(id));
		return "users/edit";
	}

	@PatchMapping("/{id}")
	public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
		usersService.update(id, user);
		return "redirect:/users";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		usersService.delete(id);
		return "redirect:/users";
	}
}