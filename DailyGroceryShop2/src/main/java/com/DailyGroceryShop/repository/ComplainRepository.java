package com.DailyGroceryShop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.DailyGroceryShop.domain.Cart;
import com.DailyGroceryShop.domain.Complain;

public interface ComplainRepository  extends JpaRepository<Complain, Integer>{

	@Query("select c FROM Complain c where c.order.customer.customerId =:customerId")
	public List<Complain> findCompliansByOrder(@Param("customerId") long customerId);
	
	
	
	/*
	 * @Query("select c FROM Complain c where c.orderedProd =:orderProdId") public
	 * Complain findCompliansByOrderedProd(@Param("orderProdId") int orderProdId);
	 */
	 
	public Complain findComplainByOrderedProd_Id( int orderedProd_id);
	  
	
}
