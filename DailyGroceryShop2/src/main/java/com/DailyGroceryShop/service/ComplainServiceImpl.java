package com.DailyGroceryShop.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DailyGroceryShop.domain.Complain;
import com.DailyGroceryShop.domain.Order;
import com.DailyGroceryShop.domain.OrderedProd;
import com.DailyGroceryShop.repository.ComplainRepository;

@Service 
public class ComplainServiceImpl implements ComplainService {

	@Autowired
	ComplainRepository complainRepository;
	
	@Override
	public Complain saveComplain(Complain complain) {
		return complainRepository.save(complain);
	}

	@Override
	public List<Complain> getAllComplains() {
		List<Complain> listOfCustomerComplains = complainRepository.findAll();
		return listOfCustomerComplains;
	}

	@Override
	public List<Complain> findCompliansByOrder(long customerId) {
		List<Complain> complainList = complainRepository.findCompliansByOrder(customerId);
		return complainList;
	}

	@Override
	public Complain findCompliansByOrderedProd(int id) {
		System.out.println("inside findCompliansByOrderedProd orderProdId " + id);
		Complain complian = complainRepository.findComplainByOrderedProd_Id(id);
		System.out.println("inside findCompliansByOrderedProd " + complian);
		return complian;
	}

	@Override
	public void resloveComplain(Complain complain) {
		System.out.println("resloveComplain "+ complain );
		Complain updateComplain = complainRepository.findById(complain.getId()).get();
		if(complain.getComplainType().equals("CANCEL ORDER")) {
			Order order = updateComplain.getOrder();
			order.setStatus("RETURNED ORDER");
			List<OrderedProd> listOfOrderedProd = order.getOrderedProductList();
			for(OrderedProd op : listOfOrderedProd) {
				op.setStatus("RETURNED ORDER");
			}
		}else if (complain.getComplainType().equals("ITEM REFUND")) {
			List<OrderedProd> listOfOrderedProd = updateComplain.getOrderedProd();
			System.out.println("listOfOrderedProd "+ listOfOrderedProd);
			for(OrderedProd op : listOfOrderedProd) {
				op.setStatus("RETURNED ITEM");
			}
		}
		updateComplain.setStatus("RESOLVED");
		updateComplain.setComplainClosedDate(LocalDate.now());
		complainRepository.save(updateComplain);
	}

	
}
