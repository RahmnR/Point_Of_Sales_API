package com.enigma.pos.repository;

import com.enigma.pos.entity.DetailOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailOrderRepository extends JpaRepository<DetailOrder, String> {
}