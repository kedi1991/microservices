package com.kedicommerce.inventoryservice.controller;

import com.kedicommerce.inventoryservice.service.InventoryService;
import jakarta.persistence.GeneratedValue;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    InventoryService inventoryService;

    @GetMapping("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@PathVariable("sku-code") String skuCode){
        // dont access the db directly, but through a service class that will call the inventory
        return inventoryService.isInStock(skuCode);
    }

}
