package com.enigma.pos.model.response;

import com.enigma.pos.model.request.ProductRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderResponse {

    private String orderDate;
    private List<ProductResponse> productRequests;
    private String customerName;
    private String employeeName;
}
