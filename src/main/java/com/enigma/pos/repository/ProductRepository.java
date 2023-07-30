package com.enigma.pos.repository;

import com.enigma.pos.entity.Category;
import com.enigma.pos.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,String> {
    @Query(value = "SELECT p FROM m_product as p WHERE code = :code", nativeQuery = true)
    Optional<Product> findProductByCode(@Param("code") String codeProduct);

    @Modifying
    @Query(value = "DELETE FROM m_product WHERE code = :code", nativeQuery = true)
    void deleteProductByCode(@Param("code") String codeProduct);

    @Query(value = "SELECT * FROM m_product",nativeQuery = true)
    List<Product> findAllProduct();
    @Modifying
    @Query(value = "UPDATE m_product SET name= :name, price = :price, category_id = :category WHERE id = :id",nativeQuery = true)
    void updateProduct(@Param("name")String name, @Param("price")Long price, @Param("category") Category category, @Param("id")String id);
}
