package com.enigma.pos.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Builder(toBuilder = true)
@Entity
@Table(name = "t_order_detail")
public class DetailOrder {
    @Id
    @GenericGenerator(strategy = "uuid2", name = "system-uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "id_detail")
    private String id;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "id")
    @JsonBackReference
    private Order order;

    public DetailOrder() {
    }

    public DetailOrder(String id, Integer quantity, Order order) {
        this.id = id;
        this.quantity = quantity;
        this.order = order;
    }

}
