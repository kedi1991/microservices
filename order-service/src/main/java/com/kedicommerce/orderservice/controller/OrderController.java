package com.kedicommerce.orderservice.controller;

import com.kedicommerce.orderservice.dto.OrderRequest;
import com.kedicommerce.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    /**
     * place an order using a dto object
     * @return
     */

    OrderService orderService;
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        //call the service to make the order
        orderService.placeOrder(orderRequest);
        return "order placed";
    }
}
