package com.enigma.pos.service;

import com.enigma.pos.entity.Order;
import com.enigma.pos.model.request.OrderRequest;
import com.enigma.pos.model.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse create(OrderRequest request);
    List<OrderResponse> viewAll();
    OrderResponse getByInvoice(String invoice);
}
