package com.kedicommerce.productservice.controller;

import com.kedicommerce.productservice.dto.ProductRequest;
import com.kedicommerce.productservice.dto.ProductResponse;
import com.kedicommerce.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    /**
     * USE th final keyword to make the value assigned at constructor level only
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){
        //productRequest is a DTO - data transfer object
        productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        /**
         * Then call a service to handle the logic
         */
        return productService.getAllProducts();
    }
}
