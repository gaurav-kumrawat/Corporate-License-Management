package com.psl.alp.ordermanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.alp.FeignClients.LicenseCatalogeFeignClient;
import com.psl.alp.ordermanagement.Entity.LicenseStatus;
import com.psl.alp.ordermanagement.Entity.Order;
import com.psl.alp.ordermanagement.repository.LicenseStatusRepository;
import com.psl.alp.ordermanagement.repository.OrderRepository;
import com.psl.alp.ordermanagement.response.LicenseStatusException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private LicenseStatusRepository licenseStatusRepository;
	
	@Autowired
	private LicenseCatalogeFeignClient licenseCatalogeFeign;

	public Order createOrder(Order order) {

		if(licenseCatalogeFeign.validateLicenseId(order.getLicenseId())) {
			Order create = orderRepository.save(order);
			int quantity = order.getQuantity();
			while (quantity-- > 0) {
				LicenseStatus licenseStatus = new LicenseStatus(order.getLicenseId());
				licenseStatus.setOrderId(create.getOrderId());
				licenseStatusRepository.save(licenseStatus);
			}
			return create;
		}
		
		throw new LicenseStatusException("licenseId is not valid");

		

	}

	public List<Order> listAllOrders() {
		return orderRepository.findAll();
	}

	public String deleteOrder(long orderId) {
		
		if(orderRepository.findById(orderId).isEmpty()) {
			throw new LicenseStatusException("Order Id not found");
		}
		List<LicenseStatus> licenseList = licenseStatusRepository.findByOrderId(orderId);
		for (LicenseStatus licenseStatus : licenseList) {
			if (licenseStatus.getLicenseKeyStatus().equals("CONSUMED")) {
				throw new LicenseStatusException("Order cannot be deleted, License Key is consumed");
			}
		}
		
		orderRepository.deleteById(orderId);
		licenseStatusRepository.deleteAll(licenseList);
		return "Order : " +orderId+" Deleted Successfully ";
	}

	public Order getOrderById(long orderId) {
		return orderRepository.findByOrderId(orderId);

	}

	public List<LicenseStatus> getLicenseById(long licenseId) {
		return licenseStatusRepository.findByLicenseId(licenseId);
	}

	public LicenseStatus updateStatus(String licenseKey) {
		try {

			LicenseStatus licenseStatus = licenseStatusRepository.findByLicenseKey(licenseKey);
			if ((licenseStatus.getLicenseKeyStatus()).equals("NOT CONSUMED")) {
				licenseStatus.setLicenseKeyStatus("CONSUMED");
			} else {
				licenseStatus.setLicenseKeyStatus("NOT CONSUMED");
			}
			return licenseStatusRepository.save(licenseStatus);
		} catch (Exception e) {
			throw new RuntimeException("License Key Not Found");
		}

	}

}
