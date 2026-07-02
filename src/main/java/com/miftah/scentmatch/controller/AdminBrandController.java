package com.miftah.scentmatch.controller;

import com.miftah.scentmatch.entity.Brand;
import com.miftah.scentmatch.service.BrandService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/brands")
public class AdminBrandController {

    private final BrandService brandService;

    public AdminBrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("brands", brandService.getAllBrands());
        return "admin/brand/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("brand", new Brand());
        return "admin/brand/form";
    }

    @PostMapping("/save")
    public String saveBrand(Brand brand) {
        brandService.saveBrand(brand);
        return "redirect:/admin/brands";
    }

    @GetMapping("/delete/{id}")
    public String deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
        return "redirect:/admin/brands";
    }
}