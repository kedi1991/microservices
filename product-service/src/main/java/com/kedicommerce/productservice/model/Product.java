package com.kedicommerce.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data //generate getters and setters for fields
@Document(value = "product") //this is a mongodb document
public class Product {

    //to store this data in the db, you have to create a repository
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
