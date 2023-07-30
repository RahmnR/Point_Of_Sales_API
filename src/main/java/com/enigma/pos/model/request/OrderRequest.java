package com.enigma.pos.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderRequest {
    private List<ProductRequest> productRequests;
    private String customerPhone;
    private String employeeEmail;
}
