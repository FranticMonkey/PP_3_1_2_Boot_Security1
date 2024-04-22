package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.util.UserValidator;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController {
    private final UserValidator userValidator;
    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public UserController(UserValidator userValidator, UserService userService, RoleService roleService) {
        this.userValidator = userValidator;
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String showUsersList(Model model) {
        model.addAttribute("users", userService.getListUsers());
        return "admin";
    }

    @GetMapping("/admin/new")
    public String addUser(@ModelAttribute("new_user") User user, Model model) {
        model.addAttribute("roles", roleService.getListRoles());
        return "addUser";
    }

    @PostMapping("/admin/new")
    public String addNewUser(@ModelAttribute("new_user") User user) {
//    public String addUser(@ModelAttribute("new_user") @Valid User user, BindingResult bindingResult) {
//        userValidator.validate(user,bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            return "addUser";
//        }
        user.setRoles(user.getRoles());
        userService.addUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String updateUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("listRoles", roleService.getListRoles());
        return "/updateUser";
    }

    @PatchMapping("/admin/edit/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String getUserPage(Model model, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }
}
