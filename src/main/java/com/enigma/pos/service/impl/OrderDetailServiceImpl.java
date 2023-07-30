package com.enigma.pos.service.impl;

import com.enigma.pos.entity.DetailOrder;
import com.enigma.pos.repository.OrderDetailRepository;
import com.enigma.pos.service.OrderDetailService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public DetailOrder create(DetailOrder detailOrder) {
        return orderDetailRepository.saveAndFlush(detailOrder);
    }
}
