package com.DailyGroceryShop.service;

import com.DailyGroceryShop.domain.Customer;

public interface CustomerService {
	
	public Customer findCustomerByUser(String userName);

}
