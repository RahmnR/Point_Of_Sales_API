package com.enigma.pos.entity;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Builder(toBuilder = true)
@Entity
@Table(name = "m_product")
public class Product {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "code", nullable = false, unique = true)
    private String codeProduct;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(columnDefinition = "bigint check (price > 0)")
    private Long price;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Product() {
    }

    public Product(String id, String codeProduct, String name, Long price, Category category ) {
        this.id = id;
        this.codeProduct = codeProduct;
        this.name = name;
        this.price = price;
        this.category = category;
    }
}
