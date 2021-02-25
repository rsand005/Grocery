package com.DailyGroceryShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DailyGroceryShop.domain.User;
import com.DailyGroceryShop.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User findUserByUserName(String userName) {
		User user = userRepository.findUserByUserName(userName);
		return user; 
	}

}
