package com.DailyGroceryShop.controller;

import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.DailyGroceryShop.dao.ProdInventoryDao;
import com.DailyGroceryShop.domain.CartItem;
import com.DailyGroceryShop.domain.Complain;
import com.DailyGroceryShop.domain.Customer;
import com.DailyGroceryShop.domain.Order;
import com.DailyGroceryShop.domain.OrderedProd;
import com.DailyGroceryShop.domain.ProdInventory;
import com.DailyGroceryShop.service.ComplainServiceImpl;
import com.DailyGroceryShop.service.CustomerServiceImpl;
import com.DailyGroceryShop.service.OrderServiceImpl;
import com.DailyGroceryShop.service.OrderedProdSreviceImpl;
import com.DailyGroceryShop.service.ProdInventoryServiceImpl;

@Controller
@RequestMapping("/order")
@SessionAttributes("CARTITEMS_SESSION")
public class OrderController {

	@Autowired
	CustomerServiceImpl customerServiceImpl;

	@Autowired
	OrderServiceImpl orderServiceImpl;
	
	@Autowired
	ProdInventoryServiceImpl prodInventoryServiceImpl;
	
	@Autowired
	ProdInventoryDao prodInventoryDao;
	
	@Autowired
	OrderedProdSreviceImpl orderedProdSreviceImpl;
	
	@Autowired
	ComplainServiceImpl complainServiceImpl;

	@RequestMapping(value = "/placeNewOrder", method = RequestMethod.GET)
	public String placeNewOrderPage(@ModelAttribute("order") Order order, BindingResult bindingResult, Model model,
			HttpServletRequest httpServletRequest, HttpSession httpSession, Principal principal) {
		System.out.println("Inside placeNewOrderPage GET order ");

		Customer customerInSession = (Customer) httpSession.getAttribute("CUSTOMER_SESSION");

		if (customerInSession == null) {
			String username = principal.getName();
			customerInSession = customerServiceImpl.findCustomerByUser(username);

		}
		System.out.println("customerInSession " + customerInSession);
		httpServletRequest.getSession().setAttribute("CUSTOMER_SESSION", customerInSession);
		
		
		prodInventoryDao.updateProdInvetoryBatch(new ArrayList<ProdInventory>());
		
		
		model.addAttribute("order", new Order());
		return "order";
	}

	@RequestMapping(value = "/saveNewOrder", method = RequestMethod.POST)
	public String saveNewOrder(@ModelAttribute("order") Order order, BindingResult bindingResult, Model model,
			HttpServletRequest httpServletRequest, HttpSession httpSession, Principal principal,
			SessionStatus sessionStatus) {
		System.out.println("Inside saveNewOrder POST order " + order);
		System.out.println("Inside saveNewOrder POST order.getDeliveryDate()" + order.getDeliveryDate());

		Customer customerInSession = (Customer) httpSession.getAttribute("CUSTOMER_SESSION");

		if (customerInSession == null) {
			String username = principal.getName();
			customerInSession = customerServiceImpl.findCustomerByUser(username);

		}
		System.out.println("customerInSession " + customerInSession);
		httpServletRequest.getSession().setAttribute("CUSTOMER_SESSION", customerInSession);

		List<CartItem> sessionCartItems = (List<CartItem>) httpServletRequest.getSession()
				.getAttribute("CARTITEMS_SESSION");
		if (sessionCartItems != null) {
			Order newOrder = new Order();
			newOrder.setDeliveryCharge(order.getDeliveryCharge());
			newOrder.setStatus(order.getStatus());
			newOrder.setDeliveryDate(LocalDate.now());
			newOrder.setOrderDate(LocalDate.now());
			newOrder.setCustomer(customerInSession);
			List<OrderedProd> orderedProductList = new ArrayList<OrderedProd>();
			List<ProdInventory> listOfProdInventory = new ArrayList<>();
			for (CartItem cartItem : sessionCartItems) {
				OrderedProd orderProd = new OrderedProd();
				orderProd.setCost(cartItem.getQuantity() * cartItem.getProduct().getPrice());
				orderProd.setDiscount(cartItem.getProduct().getProductInventory().getDiscount());
				orderProd.setFinalCost(cartItem.getQuantity() * cartItem.getProduct().getPrice()
						- cartItem.getProduct().getProductInventory().getDiscount());
				orderProd.setQuantity(cartItem.getQuantity());
				orderProd.setProduct(cartItem.getProduct());
				orderProd.setStatus("Completed");
				orderedProductList.add(orderProd);
				ProdInventory prodInventory = orderProd.getProduct().getProductInventory();
				prodInventory.setQuantity(orderProd.getProduct().getProductInventory().getQuantity() - cartItem.getQuantity());
				listOfProdInventory.add(prodInventory);
			}
			newOrder.setOrderedProductList(orderedProductList);
			Order orderPlaced = orderServiceImpl.saveOrder(newOrder);
			prodInventoryDao.updateProdInvetoryBatch(listOfProdInventory);
			if (orderPlaced.getOrderId() != 0) {
				List<CartItem> sCartItems = (List<CartItem>) httpSession.getAttribute("CARTITEMS_SESSION");
				sessionStatus.setComplete();
				System.out.println(" sCartItems "+ sCartItems);
				return "userAccount";
			}
		}

		return "order";
	}
	
	@RequestMapping(value="/orderDetails/{orderId}", method=RequestMethod.GET)
	public String viewOrderDetailsPage(@PathVariable String orderId, HttpServletRequest httpServletRequest, HttpSession httpSession, Model model) {
		System.out.println("orderDetailsPage orderId "+ orderId);
		List<Order> ordersInSession = (List<Order>)httpSession.getAttribute("ORDER_SESSION");
		Order order = (Order) ordersInSession.stream().filter(r -> r.getOrderId() == Integer.parseInt(orderId)).findFirst().orElse(null);
		System.out.println("Order after stream filter "+ order);
		model.addAttribute("order", order);
		return "orderDetails";
	}
	
	
	  @RequestMapping(value="/returnOrderedProduct", method=RequestMethod.POST)
	  public String returnOrderedProduct(@ModelAttribute("order") Order order,BindingResult bindingResult, Model model) {
	  System.out.println(" inside returnOrderedProduct POST order " + order);
	  orderedProdSreviceImpl.updateReturnedOrderedProdById(order.getOrderedProductList().get(0).getId());
	  return "orderDetails";
	  //return  "redirect: order/orderDetails/{"+ order.getOrderId() +"}"; 
	  }
	 
	@RequestMapping(value="/raiseComplain", method= RequestMethod.GET)
	public String raiseComplain(@ModelAttribute("order") Order order,BindingResult bindingResult, Model model) {
		System.out.println(" inside raiseComplain GET orderId " + order);
		model.addAttribute("complain", new Complain());
		model.addAttribute("order", order);
		String message = "";
		System.out.println("orderProd_Id " + order.getOrderedProductList().get(0).getId());
		Complain complain = complainServiceImpl.findCompliansByOrderedProd(order.getOrderedProductList().get(0).getId());
		System.out.println("complain  " + complain);
		if(complain != null) {
			message = "We have received your complain. Complain num# "+ complain.getId()  + ", and status is " + complain.getStatus() +". Description - " + complain.getDescription() +"";
		}
		model.addAttribute("message", message);
		System.out.println("message "+ message);
		return "complain1";
	}
	
	@RequestMapping(value="/saveRaisedComplain", method= RequestMethod.POST)
	  public String saveRaisedComplain(@ModelAttribute("complain") Complain complain, BindingResult bindingResult) {
	  System.out.println(" inside saveRaisedComplain POST complian " + complain);
	  complain.setComplainRaisedDate(LocalDate.now());
	  Complain savedComplain = complainServiceImpl.saveComplain(complain);
	  System.out.println("saveRaisedComplain  savedComplain "+ savedComplain);
	  return "orderDetails";
	  }
	
	
}
