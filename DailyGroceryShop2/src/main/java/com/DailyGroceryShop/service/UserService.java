package com.DailyGroceryShop.service;

import com.DailyGroceryShop.domain.User;

public interface UserService {
	
	public User findUserByUserName(String userName);

}
