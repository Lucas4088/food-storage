package com.wat.foodmanager.jpa.repositories;

import com.wat.foodmanager.jpa.entities.StorageProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageProductRepository extends JpaRepository<StorageProductEntity, Integer> {
}
