package com.kedicommerce.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    /**
     * Used to represent the Product object that wll be created
     */

    private String name;
    private String description;
    private BigDecimal price;
}
