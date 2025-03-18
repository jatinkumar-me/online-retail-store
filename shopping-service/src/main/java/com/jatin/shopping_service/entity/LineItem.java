package com.jatin.shopping_service.entity;

import lombok.Data;

@Data
public class LineItem {

	private Long itemId;
	private Long productId;
	private String productName;
	private Integer quantity;
	private Double price;
}
