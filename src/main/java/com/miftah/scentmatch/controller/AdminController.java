package com.miftah.scentmatch.controller;

import com.miftah.scentmatch.service.BrandService;
import com.miftah.scentmatch.service.CategoryService;
import com.miftah.scentmatch.service.PerfumeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PerfumeService perfumeService;
    private final BrandService brandService;
    private final CategoryService categoryService;

    public AdminController(PerfumeService perfumeService,
                           BrandService brandService,
                           CategoryService categoryService) {
        this.perfumeService = perfumeService;
        this.brandService = brandService;
        this.categoryService = categoryService;
    }

    @GetMapping({"", "/"})
    public String adminDashboard(Model model) {
        model.addAttribute("totalPerfumes", perfumeService.countPerfumes());
        model.addAttribute("totalBrands", brandService.countBrands());
        model.addAttribute("totalCategories", categoryService.countCategories());
        model.addAttribute("perfumes", perfumeService.getAllPerfumes());
        return "admin/dashboard";
    }
}