package com.enigma.pos.entity;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Builder(toBuilder = true)
@Entity
@Table(name = "m_employee")
public class Employee {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    public Employee() {
    }

    public Employee(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

}
