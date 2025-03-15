package com.jatin.shopping_service.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

	private Long orderId;
	private Long customerId;
	private List<LineItem> lineItems;
}
