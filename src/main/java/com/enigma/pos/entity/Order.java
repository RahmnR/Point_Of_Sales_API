package com.enigma.pos.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder(toBuilder = true)
@Entity
@Table(name = "t_order")
public class Order {
    @Id
    @GenericGenerator(strategy = "uuid2", name = "system-uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "id")
    private String id;

    @Column(name = "invoice", nullable = false)
    private String invoice;

    @Column(name = "date", nullable = false)
    private LocalDateTime orderDate;

    @OneToMany(mappedBy = "id")
    @JsonManagedReference
    private List<DetailOrder> detailOrders;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public Order() {
    }

    public Order(String id, String invoice, LocalDateTime orderDate, List<DetailOrder> detailOrders, Customer customer, Employee employee) {
        this.id = id;
        this.invoice = invoice;
        this.orderDate = orderDate;
        this.detailOrders = detailOrders;
        this.customer = customer;
        this.employee = employee;
    }
}
