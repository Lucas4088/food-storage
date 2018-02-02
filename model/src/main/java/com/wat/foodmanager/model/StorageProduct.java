package com.wat.foodmanager.model;

import java.time.LocalDate;
import java.util.Date;

public class StorageProduct {

    private int id;
    private String name;
    private String brand;
    private AbstractCategory category;
    private LocalDate expirationDate;
    private LocalDate dateOfPurchase;
    private Boolean expired;
    private Integer quantity;
    private Unit unit;
    private Boolean opened;
    private Date expirationDateOpened;

    private StorageProduct() {

    }

    private StorageProduct(Builder buider) {
        if (buider == null) {
            return;
        }
        this.name = buider.name;
        this.brand = buider.brand;
        this.category = buider.category;
        this.dateOfPurchase = buider.dateOfPurchase;
        this.expirationDate = buider.expirationDate;
        this.quantity = buider.quantity;
        this.unit = buider.unit;
        this.opened = false;
        this.expirationDateOpened = null;
        this.expired = false;
    }

    public StorageProduct(int id,
                          String name,
                          String brand,
                          AbstractCategory category,
                          LocalDate expirationDate,
                          LocalDate dateOfPurchase,
                          Boolean expired,
                          Integer quantity,
                          Unit unit,
                          Boolean opened,
                          Date expirationDateOpened) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.expirationDate = expirationDate;
        this.dateOfPurchase = dateOfPurchase;
        this.expired = expired;
        this.quantity = quantity;
        this.unit = unit;
        this.opened = opened;
        this.expirationDateOpened = expirationDateOpened;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public AbstractCategory getCategory() {
        return category;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public Boolean getExpired() {
        return expired;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Unit getUnit() {
        return unit;
    }

    public Boolean getOpened() {
        return opened;
    }

    public Date getExpirationDateOpened() {
        return expirationDateOpened;
    }

    public static class Builder {
        private String name;

        private String brand;
        private AbstractCategory category;
        private LocalDate dateOfPurchase;
        private LocalDate expirationDate;
        private int quantity;
        private Unit unit;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder category(AbstractCategory category) {
            this.category = category;
            return this;
        }

        public Builder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder dateOfPurchase(LocalDate dateOfPurchase) {
            this.dateOfPurchase = dateOfPurchase;
            return this;
        }

        public Builder expirationDate(LocalDate expirationDate) {
            this.expirationDate = expirationDate;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder unit(Unit unit) {
            this.unit = unit;
            return this;
        }

        public StorageProduct build() {
            return new StorageProduct(this);
        }
    }
}