package com.kedicommerce.productservice.service;

import com.kedicommerce.productservice.dto.ProductRequest;
import com.kedicommerce.productservice.dto.ProductResponse;
import com.kedicommerce.productservice.model.Product;
import com.kedicommerce.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor //creates constructor to initialise objects like the product repo below
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        /**
         *   now save in the database
         */
        productRepository.save(product);
        log.info("the product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        //find all products first from the dB
        List<Product> products = productRepository.findAll();

        /**map the Products into the ProductResponse class.
         a stream is a list of elements that can be processed sequentially
         of in parallel, apply a mapping function to each element.

         Collect the results of the mapping into a list
         */
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
