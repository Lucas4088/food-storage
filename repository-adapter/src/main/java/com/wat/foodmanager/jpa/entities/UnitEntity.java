package com.wat.foodmanager.jpa.entities;

import org.hibernate.annotations.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Proxy(lazy = false)
@Table(name = "unit")
public class UnitEntity {

    @Id
    @Column(name = "unitName", unique = true)
    private String unitName;

    public UnitEntity() {
    }

    public UnitEntity(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
