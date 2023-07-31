package com.enigma.pos.repository;

import com.enigma.pos.entity.Category;
import com.enigma.pos.entity.Product;
import com.enigma.pos.service.ProductService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,String> {
    @Modifying
    @Query(value = "INSERT INTO m_product (id,code,name,price,category_id) VALUES (:id,:code,:name,:price,:category)",nativeQuery = true)
    void createProduct(@Param("id")String id,@Param("code")String code, @Param("name")String name,
                       @Param("price")Long price, @Param("category")Category category);
    @Query(value = "SELECT * FROM m_product WHERE code = :code", nativeQuery = true)
    Optional<Product> findProductByCode(@Param("code") String codeProduct);

    @Query(value = "SELECT * FROM m_product WHERE id = :id",nativeQuery = true)
    Product findProductById(@Param("id")String id);

    @Modifying
    @Query(value = "DELETE FROM m_product WHERE code = :code", nativeQuery = true)
    void deleteProductByCode(@Param("code") String codeProduct);

    @Query(value = "SELECT * FROM m_product",nativeQuery = true)
    List<Product> findAllProduct();
    @Modifying
    @Query(value = "UPDATE m_product SET name= :name, price = :price, category_id = :category WHERE id = :id",nativeQuery = true)
    void updateProduct(@Param("name")String name, @Param("price")Long price, @Param("category") Category category, @Param("id")String id);
}
