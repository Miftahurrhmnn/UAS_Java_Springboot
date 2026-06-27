package com.miftah.scentmatch.controller;

import com.miftah.scentmatch.entity.Category;
import com.miftah.scentmatch.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryController {

    private final CategoryService categoryService;

    public AdminCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/category/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("category", new Category());
        return "admin/category/form";
    }

    @PostMapping("/save")
    public String saveCategory(Category category) {
        categoryService.saveCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/admin/categories";
    }
}