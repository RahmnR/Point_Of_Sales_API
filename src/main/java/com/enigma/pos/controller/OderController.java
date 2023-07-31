package com.enigma.pos.controller;

import com.enigma.pos.model.request.OrderRequest;
import com.enigma.pos.model.response.OrderResponse;
import com.enigma.pos.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OderController {
    private final OrderService orderService;

    @Autowired
    public OderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderResponse create(@RequestBody OrderRequest request){
        return orderService.create(request);
    }
    @GetMapping
    public List<OrderResponse> viewAll(@RequestParam(name = "invoice",required = false)String invoice){
        if (invoice==null) {
            return orderService.viewAll();
        }
        return List.of(orderService.getByInvoice(invoice));
    }

}
