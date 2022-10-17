package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

@Controller
public class UsersController {
    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/user")
    public String userMain(Model model) {
        Iterable<User> users = usersRepository.findAll();
        model.addAttribute("users", users);
        return "user/user-main";
    }

    @GetMapping("/user/add")
    public String userAdd(Model model) {
        return "user/user-add";
    }

    @PostMapping("/user/add")
    public String userPostAdd(@RequestParam String username,
                              @RequestParam Date birthdate,
                              @RequestParam Boolean gender,
                              @RequestParam int height,
                              @RequestParam double salary,
                              Model model) {
        User user = new User(username,
                birthdate,
                gender,
                height,
                salary);
        usersRepository.save(user);
        return "redirect:/user";
    }

    @GetMapping("/user/filter")
    public String userFilter(Model model) {
        return "user/user-filter";
    }

    @PostMapping("/user/filter/result")
    public String userResult(@RequestParam String title, Model model) {
        List<User> result = usersRepository.findByUsernameContains(title);
        model.addAttribute("result", result);
        return "user/user-filter";
    }
}