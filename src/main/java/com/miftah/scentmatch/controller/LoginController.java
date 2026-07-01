package com.miftah.scentmatch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(
            @RequestParam String username,
            @RequestParam String password,
            Model model) {

        if(username.equals("admin") && password.equals("admin123")) {
            return "redirect:/admin/dashboard";
        }

        model.addAttribute("error", "Username atau Password salah!");

        return "login";
    }
}