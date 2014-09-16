package com.testexercise.bookcatalog.web;

import com.testexercise.bookcatalog.domain.User;
import com.testexercise.bookcatalog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @RequestMapping(method = RequestMethod.GET)
    public String listAuthors(Model model) {
        model.addAttribute("authorsList", userService.listUsers());
        return "authors";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddForm() {
        return "userForm";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "userForm";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        return "redirect:/authors";
    }

}
