package com.wipro.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.entity.Inventory;
import com.wipro.repository.InventoryRepository;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public Inventory addInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public void deleteInventory(Long inventoryId) {
        inventoryRepository.deleteById(inventoryId);
    }

    public Inventory updateInventory(Long inventoryId, Inventory newDetails) {
        Optional<Inventory> optionalInventory = inventoryRepository.findById(inventoryId);
        if (optionalInventory.isPresent()) {
            Inventory existingInventory = optionalInventory.get();
            existingInventory.setProductId(newDetails.getProductId());
            existingInventory.setQuantity(newDetails.getQuantity());
            return inventoryRepository.save(existingInventory);
        }
        throw new RuntimeException("Inventory not found!");
    }
    
    public boolean decrementQuantity(Long productId,Integer quantity) {
  
        Inventory inventory = inventoryRepository.findByProductId(productId);
        if (inventory != null && inventory.getQuantity() > quantity) {
            inventory.setQuantity(inventory.getQuantity() - quantity);
            inventoryRepository.save(inventory);
            return true;
        }
        return false;
    }

    public Inventory getInventory(Long inventoryId) {
        return inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found!"));
    }

    public Inventory getInventoryByProductId(Long productId) {
        return Optional.ofNullable(inventoryRepository.findByProductId(productId))
                .orElseThrow(() -> new RuntimeException("Inventory not found for the given productId!"));
    }
}