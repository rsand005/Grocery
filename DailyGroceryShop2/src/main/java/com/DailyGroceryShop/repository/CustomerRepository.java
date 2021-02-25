package com.DailyGroceryShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.DailyGroceryShop.domain.Customer;

public interface CustomerRepository  extends JpaRepository<Customer, Long>{
	
	@Query("Select c from Customer c where c.user.userName =:userName")
	public Customer findCustomerByUser(@Param("userName")String userName);

}
