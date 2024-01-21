package com.kedicommerce.inventoryservice.service;

import com.kedicommerce.inventoryservice.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true) //all steps mut execute successfully or non at all.
    public boolean isInStock(String skuCode){
        //then check the repository.Tge scripts is automatically handled by spring jpa
        return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }
}
