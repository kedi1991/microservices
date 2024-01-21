package com.kedicommerce.orderservice.controller;

import com.kedicommerce.orderservice.dto.OrderRequest;
import com.kedicommerce.orderservice.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {
    /**
     * place an order using a dto object
     * @return
     */

    OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        //call the service to make the order
        orderService.placeOrder(orderRequest);
        return "order placed";
    }
}
