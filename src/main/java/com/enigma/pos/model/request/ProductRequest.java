package com.enigma.pos.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductRequest {

    private String codeProduct;
    private String productName;
    private Long price;
    private String category;
}
