package com.miftah.scentmatch.controller;

import com.miftah.scentmatch.entity.Brand;
import com.miftah.scentmatch.entity.Category;
import com.miftah.scentmatch.entity.Perfume;
import com.miftah.scentmatch.service.BrandService;
import com.miftah.scentmatch.service.CategoryService;
import com.miftah.scentmatch.service.PerfumeService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/perfumes")
public class AdminPerfumeController {

    private final PerfumeService perfumeService;
    private final BrandService brandService;
    private final CategoryService categoryService;

    public AdminPerfumeController(PerfumeService perfumeService,
                                  BrandService brandService,
                                  CategoryService categoryService) {
        this.perfumeService = perfumeService;
        this.brandService = brandService;
        this.categoryService = categoryService;
    }

    // LIST PERFUME
    @GetMapping
    public String listPerfumes(Model model) {
        model.addAttribute("perfumes", perfumeService.getAllPerfumes());
        return "admin/perfumes";
    }

    // FORM TAMBAH
    @GetMapping("/add")
    public String addPerfumeForm(Model model) {

        Perfume perfume = new Perfume();

        perfume.setBrand(new Brand());
        perfume.setCategory(new Category());

        model.addAttribute("perfume", perfume);
        model.addAttribute("brands", brandService.getAllBrands());
        model.addAttribute("categories", categoryService.getAllCategories());
        
        return "admin/perfume-form";
    }

    // SIMPAN PERFUME BARU
    @PostMapping("/save")
    public String savePerfume(HttpServletRequest request, @ModelAttribute Perfume perfume) {

        System.out.println("===========");
        System.out.println("Brand      : " + perfume.getBrand());
        System.out.println("Category   : " + perfume.getCategory());

        System.out.println(request.getParameter("brand.id"));
        System.out.println(request.getParameter("category.id"));

        if (perfume.getBrand() != null) {
            System.out.println("Brand ID" + perfume.getBrand().getId());
        }

        if (perfume.getCategory() != null) {
            System.out.println("Category ID" + perfume.getCategory().getId());
        }

        perfumeService.savePerfume(perfume);

        return "redirect:/admin/perfumes";
    }

    // FORM EDIT
    @GetMapping("/edit/{id}")
    public String editPerfumeForm(@PathVariable Long id, Model model) {
        model.addAttribute("perfume", perfumeService.getPerfumeById(id));
        model.addAttribute("brands", brandService.getAllBrands());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/perfume-form";
    }

    // UPDATE PERFUME
    @PostMapping("/update/{id}")
    public String updatePerfume(@PathVariable Long id, @ModelAttribute Perfume perfume) {
        perfumeService.updatePerfume(id, perfume);
        return "redirect:/admin/perfumes";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String deletePerfume(@PathVariable Long id) {
        perfumeService.deletePerfume(id);
        return "redirect:/admin/perfumes";
    }
}