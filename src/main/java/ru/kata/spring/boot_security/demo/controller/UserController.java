package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    private final RoleRepository roleRepository;
    private final UserService userService;

    @Autowired
    public UserController(RoleRepository roleRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String showUsersList(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("/admin/new")
    public String addUser(@ModelAttribute("new_user") User user, Model model) {
        List<Role> roles = (List<Role>) roleRepository.findAll();
        model.addAttribute("roles", roles);
        return "addUser";
    }

    @PostMapping("/admin/new")
    public String createUser(@ModelAttribute("new_user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String editUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.showUserById(id));
        List<Role> roles = (List<Role>) roleRepository.findAll();
        model.addAttribute("listRoles", roles);
        return "/updateUser";
    }

    @PatchMapping("/admin/edit/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.updateUserById(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String getUserPage(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }
}