package com.miftah.scentmatch.controller;

import com.miftah.scentmatch.entity.Perfume;
import com.miftah.scentmatch.service.BrandService;
import com.miftah.scentmatch.service.CategoryService;
import com.miftah.scentmatch.service.PerfumeService;
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
        model.addAttribute("perfume", new Perfume());
        model.addAttribute("brands", brandService.getAllBrands());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/perfume-form";
    }

    // SIMPAN PERFUME BARU
    @PostMapping("/save")
    public String savePerfume(@ModelAttribute Perfume perfume) {
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