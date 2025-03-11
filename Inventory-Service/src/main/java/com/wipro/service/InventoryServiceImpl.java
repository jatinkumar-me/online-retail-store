package com.wipro.service;

import com.wipro.dto.InventoryDTO;
import com.wipro.dto.QuantityDTO;
import com.wipro.entity.Inventory;
import com.wipro.exception.InvalidDataException;
import com.wipro.exception.ProductNotFoundException;
import com.wipro.repository.InventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    private static final Logger logger = LoggerFactory.getLogger(InventoryServiceImpl.class);

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public Inventory addInventory(Inventory inventory) {
        logger.info("Adding new inventory: {}", inventory);
        if (inventory.getQuantity() <= 0) {
            logger.error("Invalid quantity: {}", inventory.getQuantity());
            throw new InvalidDataException("Quantity must be greater than 0");
        }
        Inventory savedInventory = inventoryRepository.save(inventory);
        logger.info("Inventory added successfully: {}", savedInventory);
        return savedInventory;
    }

    @Override
    public void deleteInventory(Long inventoryId) {
        logger.info("Deleting inventory with ID: {}", inventoryId);
        if (!inventoryRepository.existsById(inventoryId)) {
            logger.error("Inventory not found with ID: {}", inventoryId);
            throw new ProductNotFoundException("Inventory not found!");
        }
        inventoryRepository.deleteById(inventoryId);
        logger.info("Inventory deleted successfully with ID: {}", inventoryId);
    }

    @Override
    public Inventory updateInventory(Long inventoryId, InventoryDTO newDetails) {
        logger.info("Updating inventory with ID: {}", inventoryId);
        Optional<Inventory> optionalInventory = inventoryRepository.findById(inventoryId);
        if (optionalInventory.isPresent()) {
            Inventory existingInventory = optionalInventory.get();
            if (newDetails.getProductId() != null) {
                existingInventory.setProductId(newDetails.getProductId());
            }
            if (newDetails.getQuantity() != null) {
                existingInventory.setQuantity(newDetails.getQuantity());
            }
            Inventory updatedInventory = inventoryRepository.save(existingInventory);
            logger.info("Inventory updated successfully: {}", updatedInventory);
            return updatedInventory;
        }
        logger.error("Inventory not found with ID: {}", inventoryId);
        throw new ProductNotFoundException("Inventory not found!");
    }

    @Override
    public boolean decrementQuantity(Long productId, QuantityDTO quantityDTO) {
        logger.info("Decrementing quantity for product ID: {}", productId);
        Inventory inventory = inventoryRepository.findByProductId(productId);
        if (inventory != null && inventory.getQuantity() > quantityDTO.getQuantity()) {
            inventory.setQuantity(inventory.getQuantity() - quantityDTO.getQuantity());
            inventoryRepository.save(inventory);
            logger.info("Product quantity decremented successfully for product ID: {}", productId);
            return true;
        }
        logger.error("Insufficient quantity or invalid product ID: {}", productId);
        throw new InvalidDataException("Insufficient quantity or invalid product ID");
    }

    @Override
    public Inventory searchInventory(Long inventoryId) {
        logger.info("Searching for inventory with ID: {}", inventoryId);
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new ProductNotFoundException("Inventory not found!"));
        logger.info("Inventory found: {}", inventory);
        return inventory;
    }

    @Override
    public Inventory searchInventoryByProductId(Long productId) {
        logger.info("Searching for inventory with product ID: {}", productId);
        Inventory inventory = Optional.ofNullable(inventoryRepository.findByProductId(productId))
                .orElseThrow(() -> new ProductNotFoundException("Inventory not found for the given productId!"));
        logger.info("Inventory found: {}", inventory);
        return inventory;
    }
}