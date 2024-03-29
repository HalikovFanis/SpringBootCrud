package com.english.springcrud.controllers;

import com.english.springcrud.models.User;
import com.english.springcrud.services.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class   UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByPrincipalUserService(principal));
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model, User user) {
        model.addAttribute("name", user.getName());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("phoneNumber", user.getPhoneNumber());
        model.addAttribute("password", user.getPassword());
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(Model model, @Valid User user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/registration";
        }
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "Пользователь с email: " + user.getEmail() + " уже существует!");
                return "/registration";
        }
        return "redirect:/login";
    }
}
