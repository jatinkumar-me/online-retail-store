package com.wipro.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wipro.dto.InventoryDTO;
import com.wipro.dto.QuantityDTO;
import com.wipro.entity.Inventory;
import com.wipro.service.InventoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/")
    public ResponseEntity<?> addInventory(@Valid @RequestBody Inventory inventory) {
        logger.info("Adding new inventory: {}", inventory);
        Inventory savedInventory = inventoryService.addInventory(inventory);
        logger.info("Inventory added successfully: {}", savedInventory);
        return new ResponseEntity<>(savedInventory, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInventory(@PathVariable Long id) {
        logger.info("Deleting inventory with ID: {}", id);
        inventoryService.deleteInventory(id);
        String message = "Inventory ID: " + id + " is successfully deleted";
        logger.info(message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInventory(@PathVariable Long id, @RequestBody InventoryDTO inventory) {
        logger.info("Updating inventory with ID: {}", id);
        Inventory updatedInventory = inventoryService.updateInventory(id, inventory);
        logger.info("Inventory updated successfully: {}", updatedInventory);
        return new ResponseEntity<>(updatedInventory, HttpStatus.OK);
    }

    @PutMapping("/{productId}/decrement")
    public ResponseEntity<?> decrementProductQuantity(@PathVariable Long productId, @RequestBody QuantityDTO quantityDTO) {
        logger.info("Decrementing quantity for product ID: {}", productId);
        boolean success = inventoryService.decrementQuantity(productId, quantityDTO);
        if (success) {
            logger.info("Product quantity decremented successfully for product ID: {}", productId);
            return ResponseEntity.ok("Product quantity decremented successfully.");
        } else {
            logger.warn("Failed to decrement product quantity for product ID: {}", productId);
            return ResponseEntity.status(400).body("Failed to decrement product quantity.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> searchInventory(@PathVariable Long id) {
        logger.info("Searching for inventory with ID: {}", id);
        Inventory inventory = inventoryService.searchInventory(id);
        logger.info("Inventory found: {}", inventory);
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<?> searchInventoryByProductId(@PathVariable Long productId) {
        logger.info("Searching for inventory with product ID: {}", productId);
        Inventory inventory = inventoryService.searchInventoryByProductId(productId);
        logger.info("Inventory found: {}", inventory);
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }
}