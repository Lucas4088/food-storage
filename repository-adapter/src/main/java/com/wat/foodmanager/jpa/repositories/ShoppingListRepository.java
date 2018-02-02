package com.wat.foodmanager.jpa.repositories;

import com.wat.foodmanager.jpa.entities.ShoppingListProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingListProductEntity, Integer> {
}
