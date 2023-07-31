package com.enigma.pos.repository;

import com.enigma.pos.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jmx.export.annotation.ManagedOperation;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,String > {
    @Query(value = "SELECT * FROM m_category as c WHERE LOWER(c.category) LIKE %:category%", nativeQuery = true)
    Optional<Category> findByCategoryIgnoreCase(@Param("category") String category);

    @Modifying
    @Query(value = "INSERT INTO m_category (id,category) VALUES (:id,:category)",nativeQuery = true)
    void createCategory(@Param("id")String id, @Param("category")String category);

    @Query(value = "SELECT * FROM m_category",nativeQuery = true)
    List<Category> findAllCategory();
    @Query(value = "SELECT * FROM m_category WHERE id = :id",nativeQuery = true)
    Category findCategoryById(@Param("id")String id);
}
