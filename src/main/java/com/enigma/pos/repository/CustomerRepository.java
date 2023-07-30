package com.enigma.pos.repository;

import com.enigma.pos.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,String > {
    @Query(value = "SELECT c FROM m_customer as c WHERE c.phone = :phone", nativeQuery = true)
    Optional<Customer> findCustomerByPhone(@Param("phone") String phone);

    @Modifying
    @Query(value = "DELETE FROM m_customer WHERE phone = :phone", nativeQuery = true)
    void deleteCustomerByPhone(@Param("phone") String phone);

    @Query(value = "SELECT * FROM m_customer",nativeQuery = true)
    List<Customer> findAllCustomer();
    @Modifying
    @Query(value = "UPDATE m_customer SET name= :name, phone = :phone WHERE id = :id",nativeQuery = true)
    void updateCustomer(@Param("name")String name,@Param("phone")String phone,@Param("id")String id);

}
