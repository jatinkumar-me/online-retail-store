package com.jatin.shopping_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jatin.shopping_service.client.CartClient;
import com.jatin.shopping_service.client.CustomerClient;
import com.jatin.shopping_service.client.InventoryClient;
import com.jatin.shopping_service.client.OrderClient;
import com.jatin.shopping_service.client.ProductClient;
import com.jatin.shopping_service.dto.InventoryDto;
import com.jatin.shopping_service.entity.Cart;
import com.jatin.shopping_service.entity.Customer;
import com.jatin.shopping_service.entity.CustomerCart;
import com.jatin.shopping_service.entity.CustomerOrder;
import com.jatin.shopping_service.entity.Inventory;
import com.jatin.shopping_service.entity.Order;
import com.jatin.shopping_service.entity.Product;
import com.jatin.shopping_service.repository.CustomerCartRepository;
import com.jatin.shopping_service.repository.CustomerOrderRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class ShoppingService {

	@Autowired
	private CustomerCartRepository customerCartRepository;

	@Autowired
	private CustomerOrderRepository customerOrderRepository;

	@Autowired
	private CartClient cartClient;

	@Autowired
	private OrderClient orderClient;

	@Autowired
	private ProductClient productClient;

	@Autowired
	private CustomerClient customerClient;

	@Autowired
	private InventoryClient inventoryClient;

	public Cart getCart(Long customerId) {
		CustomerCart customerCart = customerCartRepository.findByCustomerId(customerId)
			.orElseThrow(() -> new RuntimeException("Cart not found for customer id: " + customerId));
		return cartClient.getCart(customerCart.getCartId());
	}

	public Customer getCustomer(Long customerId) {
		return customerClient.getCustomer(customerId);
	}

	public List<Product> getProducts() {
		return productClient.getProducts();
	}

	@CircuitBreaker(fallbackMethod = "createProductFallback", name = "fallbackForCreateProduct")
	public String createProduct(InventoryDto inventoryDto) {
		Product product = Product.builder()
			.productName(inventoryDto.getProductName())
			.productDescription(inventoryDto.getProductDescription())
			.productPrice(inventoryDto.getPrice())
			.build();

		Product createdProduct = productClient.createProduct(product);

		Inventory inventory = Inventory.builder()
			.productId(createdProduct.getId())
			.quantity(inventoryDto.getQuantity())
			.build();

		inventoryClient.addInventory(inventory);

		return "Product created successfully";
	}

	public String createProductFallback(InventoryDto inventoryDto, Throwable t) {
		return "Product creation failed";
	}

	@CircuitBreaker(fallbackMethod = "addCustomerFallback", name = "fallbackForAddCustomer")
	public CustomerCart addCustomer(Customer customer) {
		Customer savedCustomer = customerClient.addCustomer(customer);
		Cart cart = new Cart();
		cart.setLineItems(new ArrayList<>());

		Cart savedCart = cartClient.addCart(cart);

		CustomerCart customerCart = CustomerCart.builder()
			.customerId(savedCustomer.getId())
			.cartId(savedCart.getCartId())
			.build();

		return customerCartRepository.save(customerCart);
	}

	public CustomerCart addCustomerFallback(Customer customer, Throwable t) {
		return CustomerCart.builder().build();
	}

	@CircuitBreaker(fallbackMethod = "addItesmToCartFallback", name = "fallbackForAddItemsToCart")
	public Cart addItemsToCart(Long customerId, Cart cart) {
		CustomerCart customerCart = customerCartRepository.findByCustomerId(customerId)
			.orElseThrow(() -> new RuntimeException("Cart not found for customer id: " + customerId));

		Cart savedCart = cartClient.updateCart(customerCart.getCartId(), cart);
		return savedCart;
	}

	public Cart addItesmToCartFallback(Long customerId, Cart cart, Throwable t) {
		return Cart.builder().build();
	}

	@CircuitBreaker(fallbackMethod = "createOrderFallback", name = "fallbackForCreateOrder")
	public Order createOrder(Long customerId) {
		CustomerCart customerCart = customerCartRepository.findByCustomerId(customerId)
			.orElseThrow(() -> new RuntimeException("Cart not found for customer id: " + customerId));

		Cart cart = cartClient.getCart(customerCart.getCartId());

		if (cart.getLineItems().isEmpty()) {
			throw new RuntimeException("Cart is empty");
		}

		//Reduce the product quantity from inventory
		cart.getLineItems().forEach(lineItem -> {
			Inventory inventory = inventoryClient.getInventoryByProductId(lineItem.getProductId());
			int newQuantity = inventory.getQuantity() - lineItem.getQuantity();
			if (newQuantity < 0) {
				throw new RuntimeException("Product quantity is not available in inventory");
			}
			inventory.setQuantity(newQuantity);
			inventoryClient.updateInventory(inventory.getInventoryId(), inventory);
		});

		Order order = Order.builder()
			.customerId(customerId)
			.lineItems(cart.getLineItems())
			.build();

		Order savedOrder = orderClient.createOrder(order);

		CustomerOrder customerOrder = CustomerOrder.builder()
			.customerId(customerId)
			.orderId(savedOrder.getOrderId())
			.build();

		customerOrderRepository.save(customerOrder);

		cartClient.emptyCart(customerCart.getCartId());

		return savedOrder;
	}

	public Order createOrderFallback(Long customerId, Throwable t) {
		return Order.builder().build();
	}

	@CircuitBreaker(fallbackMethod = "getAllOrdersFallback", name = "fallbackForGetAllOrders")
	public List<Order> getAllOrders(Long customerId) {
		List<CustomerOrder> customerOrders = customerOrderRepository.findByCustomerId(customerId);
		List<Order> orders = new ArrayList<>();
		customerOrders.forEach(customerOrder -> {
			Order order = orderClient.getOrder(customerOrder.getOrderId());
			orders.add(order);
		});
		return orders;
	}

	public List<Order> getAllOrdersFallback(Long customerId, Throwable t) {
		return new ArrayList<>();
	}
}
