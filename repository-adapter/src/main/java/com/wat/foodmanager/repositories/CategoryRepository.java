package com.wat.foodmanager.repositories;

import com.wat.foodmanager.dao.CategoryDao;
import com.wat.foodmanager.entities.CategoryEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CategoryRepository implements CategoryDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addCategory(CategoryEntity category) {
        entityManager.persist(category);
    }

    @Override
    public List<CategoryEntity> listCategories() {
        CriteriaQuery<CategoryEntity> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(CategoryEntity.class);
        Root<CategoryEntity> root = criteriaQuery.from(CategoryEntity.class);
        return entityManager.createQuery(criteriaQuery).getResultList();

    }
}
