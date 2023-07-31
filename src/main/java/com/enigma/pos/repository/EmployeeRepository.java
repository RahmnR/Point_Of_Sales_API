package com.enigma.pos.repository;

import com.enigma.pos.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,String> {

    @Modifying
    @Query(value = "INSERT INTO m_employee (id,email,name) VALUES (:id, :email, :name)",nativeQuery = true)
    void createEmployee(@Param("id")String id, @Param("email")String email, @Param("name")String name);

    @Query(value = "SELECT * FROM m_employee WHERE id = :id",nativeQuery = true)
    Employee findEmployeeById(@Param("id")String id);

    @Query(value = "SELECT * FROM m_employee as e WHERE e.email= :email", nativeQuery = true)
    Optional<Employee> findEmployeeByEmail(@Param("email") String email);

    @Modifying
    @Query(value = "DELETE FROM m_employee WHERE email = :email", nativeQuery = true)
    void deleteEmployeeByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM m_employee",nativeQuery = true)
    List<Employee> findAllEmployee();
    @Modifying
    @Query(value = "UPDATE m_employee SET name= :name WHERE email = :email",nativeQuery = true)
    void updateEmployeeBy(@Param("name")String name,@Param("email")String email);
}
