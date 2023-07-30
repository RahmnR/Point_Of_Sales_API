package com.enigma.pos.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class DetailOrderResponse {
    private String codeProduct;
    private String productName;
    private Long price;
    private Long subTotal;
}
