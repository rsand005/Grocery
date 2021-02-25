package com.DailyGroceryShop.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.DailyGroceryShop.domain.Customer;
import com.DailyGroceryShop.domain.Order;

public interface OrderRespository extends JpaRepository<Order, Integer> {

	@Query("Select r From Order r where r.customer.customerId =:customerId")
	public List<Order> getOrdersByCustomer(@Param("customerId") long customerId);
	
	 
}
