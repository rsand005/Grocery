package com.DailyGroceryShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DailyGroceryShop.domain.Customer;
import com.DailyGroceryShop.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Customer findCustomerByUser(String userName) {
		System.out.println("findCustomerByUser "+ userName );
		Customer customer = customerRepository.findCustomerByUser(userName);
		return customer;
	}

}
