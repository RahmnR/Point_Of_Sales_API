package com.enigma.pos.repository;

import com.enigma.pos.entity.Employee;
import com.enigma.pos.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,String> {
    @Query(value = "SELECT e FROM m_employee as e WHERE email= :email", nativeQuery = true)
    Optional<Employee> findEmployeeByEmail(@Param("email") String email);

    @Modifying
    @Query(value = "DELETE FROM m_employee WHERE email = :email", nativeQuery = true)
    void deleteEmployeeByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM m_employee",nativeQuery = true)
    List<Employee> findAllEmployee();
    @Modifying
    @Query(value = "UPDATE m_employee SET name= :name WHERE email = :email",nativeQuery = true)
    void updateEmployee(@Param("name")String name,@Param("email")String email);
}
