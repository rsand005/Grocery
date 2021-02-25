package com.DailyGroceryShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DailyGroceryShop.domain.OrderedProd;

public interface OrderedProdRepository extends JpaRepository<OrderedProd, Integer >{

}
