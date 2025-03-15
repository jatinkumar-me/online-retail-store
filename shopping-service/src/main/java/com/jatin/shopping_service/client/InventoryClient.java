package com.jatin.shopping_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jatin.shopping_service.entity.Inventory;

@FeignClient(name = "inventory-service")
public interface InventoryClient {

    @PostMapping("/api/inventory")
    public Inventory addInventory(@RequestBody Inventory inventory);

    @GetMapping("/api/inventory/{productId}")
    public Inventory getInventoryByProductId(@PathVariable Long productId);

    @PutMapping("/api/inventory/{id}")
    void updateInventory(@PathVariable Long id, @RequestBody Inventory inventory);
}
