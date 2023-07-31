package com.enigma.pos.repository;

import com.enigma.pos.entity.DetailOrder;
import com.enigma.pos.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<DetailOrder, String> {

    @Query(value = "SELECT * FROM t_order_detail",nativeQuery = true)
    List<DetailOrder> findAllDetail();

    @Query(value = "SELECT * FROM t_order_detail WHERE id_detail = :id",nativeQuery = true)
    DetailOrder findDetailById(@Param("id")String id);

    @Modifying
    @Query(value = "INSERT INTO t_order_detail (id_detail,price,product,product_code,quantity,order_id) " +
            "VALUES (:id_detail, :price, :product, :code, :quantity, :orderId)",nativeQuery = true)
    void createDetail(@Param("id_detail") String id, @Param("price") Long price, @Param("product") String productName,
                               @Param("code") String code, @Param("quantity") Integer qty, @Param("orderId") Order orderId);
}
