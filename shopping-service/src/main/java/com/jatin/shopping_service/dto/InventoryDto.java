package com.jatin.shopping_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDto {

	private String productName;
	private String productDescription;
	private Integer quantity;
	private Integer price;
}
