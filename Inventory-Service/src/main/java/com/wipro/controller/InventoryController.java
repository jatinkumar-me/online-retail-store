package com.wipro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.entity.Inventory;
import com.wipro.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/")
    public ResponseEntity<Inventory> addInventory(@RequestBody Inventory inventory) {
        Inventory savedInventory = inventoryService.addInventory(inventory);
        return new ResponseEntity<>(savedInventory, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable Long id, @RequestBody Inventory inventory) {
        Inventory updatedInventory = inventoryService.updateInventory(id, inventory);
        return new ResponseEntity<>(updatedInventory, HttpStatus.OK);
    }
    
    @PutMapping("/{productId}/decrement")
    public ResponseEntity<String> decrementProductQuantity(@PathVariable Long productId, @RequestBody Integer quantity) {
        boolean success = inventoryService.decrementQuantity(productId, quantity);
        if (success) {
            return ResponseEntity.ok("Product quantity decremented successfully.");
        } else {
            return ResponseEntity.status(400).body("Failed to decrement product quantity.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventory(@PathVariable Long id) {
        Inventory inventory = inventoryService.getInventory(id);
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Inventory> getInventoryByProductId(@PathVariable Long productId) {
        Inventory inventory = inventoryService.getInventoryByProductId(productId);
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }
}