package com.apap.tutorial8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial8.model.UserRoleModel;
import com.apap.tutorial8.service.UserRoleService;

@Controller
@RequestMapping("/user")
public class UserRoleController {
	@Autowired
	private UserRoleService userService;
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	private String addUserSubmit(@ModelAttribute UserRoleModel user) {
		userService.addUser(user);
		return "home";
	}
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.GET)
	private String updateUser(@RequestParam("username") String username,Model model) {
		UserRoleModel user = userService.getUserByUsername(username);
		model.addAttribute("user", user);
		return "update-user";
	}
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	private String updateUserSubmit(@RequestParam("username") String username, @ModelAttribute("newPassword") String newPassword) {
		UserRoleModel usr = userService.getUserByUsername(username);
		userService.updateUser(usr, newPassword);
		return "home";
	}
}
