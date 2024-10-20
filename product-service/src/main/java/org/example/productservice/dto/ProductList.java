package org.example.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
@Getter
@AllArgsConstructor
public class ProductList {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
