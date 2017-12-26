package com.wat.foodmanager.model;

import java.util.Date;

public class StoredProduct {
    private String name;
    private String brand;
    private Category category;
    private Date expirationDate;
    private Date dateOfPurchase;
    private Boolean expired;
    private Integer quantity;
    private Unit unit;
    private Boolean opened;
    private Date ExpirationDateOpened;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
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

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
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
