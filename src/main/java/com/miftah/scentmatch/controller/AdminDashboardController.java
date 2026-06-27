package com.miftah.scentmatch.controller;

import com.miftah.scentmatch.service.BrandService;
import com.miftah.scentmatch.service.CategoryService;
import com.miftah.scentmatch.service.PerfumeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminDashboardController {

    private final PerfumeService perfumeService;
    private final BrandService brandService;
    private final CategoryService categoryService;

    public AdminDashboardController(
            PerfumeService perfumeService,
            BrandService brandService,
            CategoryService categoryService
    ) {
        this.perfumeService = perfumeService;
        this.brandService = brandService;
        this.categoryService = categoryService;
    }

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalPerfumes", perfumeService.countPerfumes());
        model.addAttribute("totalBrands", brandService.countBrands());
        model.addAttribute("totalCategories", categoryService.countCategories());
        model.addAttribute("perfumes", perfumeService.getAllPerfumes());

        return "admin/dashboard";
    }
}