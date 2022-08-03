package com.psl.alp.ordermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psl.alp.ordermanagement.Entity.LicenseStatus;
import com.psl.alp.ordermanagement.Entity.Order;
import com.psl.alp.ordermanagement.service.OrderService;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

	@Autowired
	private OrderService orderService;
    
	@PostMapping("/orders")
	public Order createOrder(@RequestBody Order order) {
		return orderService.createOrder(order);
	}

	@GetMapping("/orders")
	public List<Order> listAll() {
		return orderService.listAllOrders();
	}
	
	@GetMapping("/orders/{orderId}")
	public Order getOrderById(@PathVariable long orderId) {
		return orderService.getOrderById(orderId);
	}
	
	@GetMapping("/orders/licenses/{licenseId}")
	public List<LicenseStatus> getLicenseById(@PathVariable long licenseId) {
		return orderService.getLicenseById(licenseId);
	}
	

	@DeleteMapping("/orders/{orderId}")
	public String deleteOrder(@PathVariable long orderId) {
		return orderService.deleteOrder(orderId);
	}

	@PutMapping("/orders/license_keys/{licenseKey}")
	public LicenseStatus updateStatus(@PathVariable String licenseKey) {	
		return orderService.updateStatus(licenseKey);
	}

}
