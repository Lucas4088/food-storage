package com.wat.foodmanager.jpa.entities;

import org.hibernate.annotations.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Proxy(lazy = false)
@Table(name = "category")
public class CategoryEntity {

    @Id
    @Column(name = "categoryName", unique = true)
    private String categoryName;

    public CategoryEntity(String categoryName) {
        this.categoryName = categoryName;
    }

    public CategoryEntity() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
