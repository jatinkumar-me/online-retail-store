package com.jatin.shopping_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

	private Long productId;
	private String productName;
	private String productDescription;
	private Integer productPrice;
}
