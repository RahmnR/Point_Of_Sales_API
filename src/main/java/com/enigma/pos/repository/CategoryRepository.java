package com.enigma.pos.repository;

import com.enigma.pos.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,String > {
    @Query(value = "SELECT c FROM m_category as c WHERE LOWER(c.category) LIKE %:category%", nativeQuery = true)
    Optional<Category> findByCategoryIgnoreCase(@Param("category") String category);
}
