package com.enigma.pos.repository;

import com.enigma.pos.entity.Customer;
import com.enigma.pos.entity.Employee;
import com.enigma.pos.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,String> {

    @Modifying
    @Query(value = "INSERT INTO t_order (id, invoice, date, customer_id, employee_id) " +
            "VALUES (:id, :invoice, :date, :customer, :employee)", nativeQuery = true)
    void createOrder(@Param("id") String id, @Param("invoice") String invoice, @Param("date") LocalDateTime date,
                     @Param("customer") Customer customer, @Param("employee") Employee employee);

    @Query(value = "SELECT * FROM t_order",nativeQuery = true)
    List<Order> findAllOrder();

    @Query(value = "SELECT * FROM t_order as o WHERE invoice = :invoice",nativeQuery = true)
    Order findByInvoice(@Param("invoice")String invoice);

}
