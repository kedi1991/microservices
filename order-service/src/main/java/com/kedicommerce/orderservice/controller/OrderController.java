package com.kedicommerce.orderservice.controller;

import com.kedicommerce.orderservice.dto.OrderRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    /**
     * place an order using a dto object
     * @return
     */
    public String placeOrder(@RequestBody OrderRequest orderRequest){

        return "order placed";
    }
}
