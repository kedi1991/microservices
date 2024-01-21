package com.kedicommerce.inventoryservice;

import com.kedicommerce.inventoryservice.model.Inventory;
import com.kedicommerce.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean //run this at program start to create the objects
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("Tecno 15");
			inventory1.setQuantity(230);

			Inventory inventory2 = new Inventory();
			inventory2.setSkuCode("Samsung Galaxy S23");
			inventory2.setQuantity(120);

			//then save to the repository
			inventoryRepository.save(inventory1);
			inventoryRepository.save(inventory2);
		};
	}
}
