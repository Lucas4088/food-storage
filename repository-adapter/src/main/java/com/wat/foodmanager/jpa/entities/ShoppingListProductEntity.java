package com.wat.foodmanager.jpa.entities;


import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Proxy(lazy = false)
@Table(name = "ShoppingListProduct")
public class ShoppingListProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private CategoryEntity category;
    @Column(name = "quantity")
    private Integer quantity;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private UnitEntity unit;

    public ShoppingListProductEntity() {
    }

    public ShoppingListProductEntity(String name, CategoryEntity category, Integer quantity, UnitEntity unit) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.unit = unit;
    }

    public ShoppingListProductEntity(int id, String name, CategoryEntity category, Integer quantity, UnitEntity unit) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public UnitEntity getUnit() {
        return unit;
    }

    public void setUnit(UnitEntity unit) {
        this.unit = unit;
    }
}
