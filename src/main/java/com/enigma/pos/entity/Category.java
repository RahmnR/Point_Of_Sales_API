package com.enigma.pos.entity;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Builder(toBuilder = true)
@Entity
@Table(name = "m_category")
public class Category {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "category")
    private String category;

    public Category() {
    }

    public Category(String id, String category) {
        this.id = id;
        this.category = category;
    }
}
