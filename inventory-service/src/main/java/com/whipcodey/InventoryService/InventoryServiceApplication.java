package com.whipcodey.InventoryService;

import com.whipcodey.InventoryService.model.Inventory;
import com.whipcodey.InventoryService.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("iPhone_13");
			inventory1.setQuantity(100);

			Inventory inventory2 = new Inventory();
			inventory2.setSkuCode("iPhone_13_Red");
			inventory2.setQuantity(0);

			inventoryRepository.save(inventory1);
			inventoryRepository.save(inventory2);
		};
	}

}
