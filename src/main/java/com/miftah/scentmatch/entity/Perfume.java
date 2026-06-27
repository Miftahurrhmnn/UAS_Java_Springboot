package com.miftah.scentmatch.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "perfumes")
public class Perfume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "size_ml")
    private Integer sizeMl;

    private String gender;

    @Column(name = "top_notes", columnDefinition = "TEXT")
    private String topNotes;

    @Column(name = "middle_notes", columnDefinition = "TEXT")
    private String middleNotes;

    @Column(name = "base_notes", columnDefinition = "TEXT")
    private String baseNotes;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(nullable = false)
    private Integer stock = 0;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // ===== GETTER & SETTER =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {   // PENTING buat edit/update
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSizeMl() {
        return sizeMl;
    }

    public void setSizeMl(Integer sizeMl) {
        this.sizeMl = sizeMl;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTopNotes() {
        return topNotes;
    }

    public void setTopNotes(String topNotes) {
        this.topNotes = topNotes;
    }

    public String getMiddleNotes() {
        return middleNotes;
    }

    public void setMiddleNotes(String middleNotes) {
        this.middleNotes = middleNotes;
    }

    public String getBaseNotes() {
        return baseNotes;
    }

    public void setBaseNotes(String baseNotes) {
        this.baseNotes = baseNotes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}