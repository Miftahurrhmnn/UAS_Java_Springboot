package com.miftah.scentmatch.controller;

import com.miftah.scentmatch.service.PerfumeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final PerfumeService perfumeService;

    public UserController(PerfumeService perfumeService) {
        this.perfumeService = perfumeService;
    }

    @GetMapping("/")
    public String home(Model model,
                       @RequestParam(value = "success", required = false) String success,
                       @RequestParam(value = "error", required = false) String error) {
        model.addAttribute("perfumes", perfumeService.getAllPerfumes());
        model.addAttribute("success", success);
        model.addAttribute("error", error);
        return "user/home";
    }

    @PostMapping("/buy/{id}")
    public String buyPerfume(@PathVariable Long id) {
        boolean result = perfumeService.buyPerfume(id);

        if (result) {
            return "redirect:/?success=Parfum berhasil dibeli";
        } else {
            return "redirect:/?error=Stok parfum habis atau parfum tidak ditemukan";
        }
    }
}