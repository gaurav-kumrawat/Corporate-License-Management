package com.psl.alp.ordermanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psl.alp.ordermanagement.Entity.LicenseStatus;
import com.psl.alp.ordermanagement.Entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	Order findByOrderId(long orderId);

}
