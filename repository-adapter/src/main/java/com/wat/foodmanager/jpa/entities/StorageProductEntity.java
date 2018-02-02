package com.wat.foodmanager.jpa.entities;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Proxy(lazy = false)
@Table(name = "StorageProduct")
public class StorageProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "brand")
    private String brand;
    @ManyToOne(cascade = CascadeType.MERGE)
    private CategoryEntity category;
    @Column(name = "expirationDate")
    private LocalDate expirationDate;
    @Column(name = "dateOfPurchase")
    private LocalDate dateOfPurchase;
    @Column(name = "expired")
    private Boolean expired;
    @Column(name = "quantity")
    private Integer quantity;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(referencedColumnName = "unitName")
    private UnitEntity unit;
    @Column(name = "opened")
    private Boolean opened;
    @Column(name = "ExpirationDateOpened")
    private Date ExpirationDateOpened;

    public StorageProductEntity() {
        expired = false;
    }

    public StorageProductEntity(String name, String brand, CategoryEntity category, LocalDate expirationDate, LocalDate dateOfPurchase, Integer quantity, UnitEntity unit, Boolean opened, Date expirationDateOpened) {
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.expirationDate = expirationDate;
        this.dateOfPurchase = dateOfPurchase;
        this.expired = false;
        this.quantity = quantity;
        this.unit = unit;
        this.opened = opened;
        ExpirationDateOpened = expirationDateOpened;
    }

    public StorageProductEntity(int id, String name, String brand, CategoryEntity category, LocalDate expirationDate, LocalDate dateOfPurchase, Integer quantity, UnitEntity unit, Boolean opened, Date expirationDateOpened) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.expirationDate = expirationDate;
        this.dateOfPurchase = dateOfPurchase;
        this.expired = false;
        this.quantity = quantity;
        this.unit = unit;
        this.opened = opened;
        ExpirationDateOpened = expirationDateOpened;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
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

    public Boolean getOpened() {
        return opened;
    }

    public void setOpened(Boolean opened) {
        this.opened = opened;
    }

    public Date getExpirationDateOpened() {
        return ExpirationDateOpened;
    }

    public void setExpirationDateOpened(Date expirationDateOpened) {
        ExpirationDateOpened = expirationDateOpened;
    }
}
