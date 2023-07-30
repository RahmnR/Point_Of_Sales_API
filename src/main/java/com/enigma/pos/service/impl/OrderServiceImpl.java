package com.enigma.pos.service.impl;

import com.enigma.pos.entity.*;
import com.enigma.pos.model.request.OrderDetailRequest;
import com.enigma.pos.model.request.OrderRequest;
import com.enigma.pos.model.response.DetailOrderResponse;
import com.enigma.pos.model.response.OrderResponse;
import com.enigma.pos.repository.OrderRepository;
import com.enigma.pos.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailService orderDetailService;
    private final EmployeeService employeeService;
    private final CustomerService customerService;
    private final ProductService productService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderDetailService orderDetailService,
                            EmployeeService employeeService, CustomerService customerService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.orderDetailService = orderDetailService;
        this.employeeService = employeeService;
        this.customerService = customerService;
        this.productService = productService;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public OrderResponse create(OrderRequest request) {
        try {
            Customer customer = customerService.getByPhone(request.getCustomerPhone());
            Employee employee = employeeService.getEmploye(request.getEmployeeEmail());
            String invoice = generateInvoice(LocalDate.now());
            Order order = Order.builder().build();

            orderRepository.createOrder(order.getId(),invoice,LocalDateTime.now(),customer,employee);
            Order orderSaved = orderRepository.findByInvoice(invoice);

            List<OrderDetailRequest> orderDetailRequests = request.getOrderDetailRequests();
            List<DetailOrder> detailOrders = orderDetailRequests.stream().map(product -> {
                Product productByCode = productService.getProductByCode(product.getCodeProduct());

                return orderDetailService.create(DetailOrder.builder()
                        .productCode(product.getCodeProduct())
                        .product(productByCode.getName())
                        .quantity(product.getQuantity())
                        .price(product.getPrice())
                        .order(orderSaved)
                        .build());
            }).collect(Collectors.toList());

            return toOrderResponse(orderSaved,detailOrders);
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<OrderResponse> viewAll() {
        return orderRepository.findAllOrder().stream().map(order -> {
            return toOrderResponse(order, order.getDetailOrders());
        }).collect(Collectors.toList());
    }

    private String generateInvoice(LocalDate dateTime){
        int size = viewAll().size()+1;
        return "INV/"+dateTime.getDayOfWeek()+"/"+size;
    }
    private OrderResponse toOrderResponse(Order order, List<DetailOrder> detailOrder){
        List<DetailOrderResponse> orderResponses = detailOrder.stream().map(detail -> {
            return DetailOrderResponse.builder()
                    .codeProduct(detail.getProductCode())
                    .productName(detail.getProduct())
                    .price(detail.getPrice())
                    .subTotal(detail.getPrice() * detail.getQuantity())
                    .build();

        }).collect(Collectors.toList());

        return OrderResponse.builder()
                .orderDate(order.getOrderDate().toLocalDate().toString())
                .detailResponse(orderResponses)
                .customerName(order.getCustomer().getName())
                .employeeName(order.getEmployee().getName())
                .build();
    }
}
