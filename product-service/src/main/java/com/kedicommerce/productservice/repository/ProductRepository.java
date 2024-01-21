package com.kedicommerce.productservice.repository;

import com.kedicommerce.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
