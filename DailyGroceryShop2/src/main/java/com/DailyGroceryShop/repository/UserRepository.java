package com.DailyGroceryShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DailyGroceryShop.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findUserByUserName(String userName);
}
