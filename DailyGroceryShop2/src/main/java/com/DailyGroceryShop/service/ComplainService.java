package com.DailyGroceryShop.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.DailyGroceryShop.domain.Complain;

public interface ComplainService {

	public Complain saveComplain(Complain complain);
	public List<Complain> findCompliansByOrder(long customerId);
	public Complain findCompliansByOrderedProd(int orderProdId);
	public List<Complain> getAllComplains();
	public void resloveComplain(Complain comlain);
}
