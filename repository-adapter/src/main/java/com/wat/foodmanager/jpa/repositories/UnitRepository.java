package com.wat.foodmanager.jpa.repositories;

import com.wat.foodmanager.jpa.entities.UnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<UnitEntity, Integer> {
    UnitEntity getByUnitName(String name);
}
