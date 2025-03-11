package com.wipro.service;

import com.wipro.dto.InventoryDTO;
import com.wipro.dto.QuantityDTO;
import com.wipro.entity.Inventory;

public interface InventoryService {
    Inventory addInventory(Inventory inventory);
    void deleteInventory(Long inventoryId);
    Inventory updateInventory(Long inventoryId, InventoryDTO newDetails);
    boolean decrementQuantity(Long productId, QuantityDTO quantityDTO);
    Inventory searchInventory(Long inventoryId);
    Inventory searchInventoryByProductId(Long productId);
}
