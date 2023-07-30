package com.enigma.pos.repository;

import com.enigma.pos.entity.DetailOrder;
import com.enigma.pos.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderDetailRepository extends JpaRepository<DetailOrder, String> {
    @Modifying
    @Query(value = "INSERT INTO t_order_detail (product_code,product,price,quantity,order) " +
            "VALUES (:productCode,:product,:price,:quantity,:order)",nativeQuery = true)
    void createNewDetail(@Param("productCode")String code, @Param("product")String product,
                         @Param("price")String price, @Param("quantity")Integer qty,
                         @Param("prder")Order order);
}
