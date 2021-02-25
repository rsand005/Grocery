package com.DailyGroceryShop.controller;

import java.security.Principal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.DailyGroceryShop.domain.Cart;
import com.DailyGroceryShop.domain.CartItem;
import com.DailyGroceryShop.domain.Complain;
import com.DailyGroceryShop.domain.Customer;
import com.DailyGroceryShop.domain.Order;
import com.DailyGroceryShop.domain.User;
import com.DailyGroceryShop.service.CartItemServiceImpl;
import com.DailyGroceryShop.service.CartServiceImpl;
import com.DailyGroceryShop.service.ComplainServiceImpl;
import com.DailyGroceryShop.service.CustomerServiceImpl;
import com.DailyGroceryShop.service.OrderServiceImpl;
import com.DailyGroceryShop.service.UserServiceImpl;

@Controller
@RequestMapping("/user")
@SessionAttributes("CARTITEMS_SESSION")
public class UserController {

	@Autowired
	CartItemServiceImpl cartItemServiceImpl;

	@Autowired
	CustomerServiceImpl customerServiceImpl;

	@Autowired
	CartServiceImpl cartServiceImpl;

	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired
	OrderServiceImpl orderServiceImpl;
	
	@Autowired
	ComplainServiceImpl complainServiceImpl;

	@RequestMapping(value = "/userAccount", method = RequestMethod.GET)
	public String userAccountPage(Principal principal, Model model, HttpServletRequest httpServletRequest, HttpSession httpSession) {
		String username = principal.getName();
		Customer customer = customerServiceImpl.findCustomerByUser(username);
		Customer customerInSession = (Customer)httpSession.getAttribute("CUSTOMER_SESSION");
		List<Order> ordersInSession = (List<Order>)httpSession.getAttribute("ORDER_SESSION");
		List<Complain> complainInSession = (List<Complain>)httpSession.getAttribute("COMPLAIN_SESSION");
		  if(customerInSession == null && customer != null) { 
			  customerInSession =  customerServiceImpl.findCustomerByUser(username); 
			  
		  }
		  if(customer != null) { 
			  System.out.println("ordersInSession == null && customer != null ");
			  ordersInSession =  orderServiceImpl.getOrdersByCustomer(customer.getCustomerId());
			  
		  }
		  if(complainInSession == null && customer != null) {
			  complainInSession = complainServiceImpl.findCompliansByOrder(customer.getCustomerId());
		  }
		System.out.println("customerInSession "+ customerInSession);
		System.out.println("complainInSession "+ complainInSession);
		httpServletRequest.getSession().setAttribute("CUSTOMER_SESSION", customerInSession);
		httpServletRequest.getSession().setAttribute("ORDER_SESSION", ordersInSession);
		System.out.println("ordersInSession userAccountPage " + ordersInSession);
		model.addAttribute("customer", customer);
		return "userAccount";
	}

	@RequestMapping(value = "/cart", method = {RequestMethod.GET, RequestMethod.POST})
	public String userCartPage(@ModelAttribute("cartItem") CartItem cartItem, BindingResult bindingResult, Model model,
			HttpServletRequest request, HttpSession session, Principal principal) {
		System.out.println("inside userCartPage GET " + cartItem);
		List<CartItem> sCartItems = (List<CartItem>) session.getAttribute("CARTITEMS_SESSION");
		System.out.println(" beofre If sCartItems " + sCartItems);
		/*
		 * if (sCartItems == null) {
		 * System.out.println(" if(sCartItems == null)  sCartItems " + sCartItems); User
		 * currLoginUser = userServiceImpl.findUserByUserName(principal.getName());
		 * System.out.println(" if(sCartItems == null)  currLoginUser " +
		 * currLoginUser); Cart cart =
		 * cartServiceImpl.getCartByUser(currLoginUser.getUserId());
		 * System.out.println(" if(sCartItems == null)  cart " + cart); sCartItems =
		 * cartItemServiceImpl.getAllCartItem(); System.out.
		 * println(" if(sCartItems == null)  cartItemServiceImpl.getAllCartItem() " +
		 * sCartItems); }
		 */
		if (cartItem.getProduct() != null) {
			System.out.println(" if(sCartItems == null)  cart ");
			sCartItems.add(cartItem);
		}
		request.getSession().setAttribute("CARTITEMS_SESSION", sCartItems);
		model.addAttribute("sessionCartItems", sCartItems != null ? sCartItems : new ArrayList<CartItem>());
		/*
		 * List<CartItem> listOfCartItems = cartItemServiceImpl.getAllCartItem();
		 * model.addAttribute("listOfCartItems", listOfCartItems);
		 */
		return "cart";
	}

	@RequestMapping(value = "/saveCartItemFromHome", method = RequestMethod.POST)
	public String updateUserCartItem(@ModelAttribute("cartItem") CartItem cartItem, BindingResult bindingResult,
			Model model, HttpServletRequest request, Principal principal) {
		System.out.println("inside saveOrUpdateCartItem POST " + cartItem);

		List<CartItem> sessionCartItems = (List<CartItem>) request.getSession().getAttribute("CARTITEMS_SESSION");
		if (sessionCartItems == null) {
			sessionCartItems = new CopyOnWriteArrayList<CartItem>();
			request.getSession().setAttribute("CARTITEMS_SESSION", sessionCartItems);
		}
		System.out.println("inside before adding cartItem to sessionCartItem  " + cartItem);
		findAndUpdateQtyInSessionCartItems(sessionCartItems, cartItem);
		request.getSession().setAttribute("CARTITEMS_SESSION", sessionCartItems);
		/*
		 * if(bindingResult.hasErrors()) { return "redirect:/user"; }
		 * cartItemServiceImpl.findAndUpdateCartItemQty(cartItem);
		 */
		return "redirect:/home";
	}

	@RequestMapping(value = "/updateCartItemQtyFromCart", method = {RequestMethod.GET, RequestMethod.POST})
	public String updateCartItemQtyFromCart(@ModelAttribute("cartItem") CartItem cartItem, BindingResult bindingResult,
			Model model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Principal principal) {

		System.out.println("updateCartItemQtyFromCart  " + cartItem);

		List<CartItem> sessionCartItems = (List<CartItem>) httpServletRequest.getSession()
				.getAttribute("CARTITEMS_SESSION");
		for (CartItem cItem : sessionCartItems) {
			if (cItem.getProduct().getProductId() == cartItem.getProduct().getProductId()) {
				cItem.setQuantity(cartItem.getQuantity());
			}
		}

		// findAndUpdateQtyInSessionCartItems(sessionCartItems, cartItem);
		httpServletRequest.getSession().setAttribute("CARTITEMS_SESSION", sessionCartItems);
		return "cart";
	}

	public void findAndUpdateQtyInSessionCartItems(List<CartItem> sessionCartItems, CartItem cartItem) {
		boolean newCItem = true;
		for (CartItem cItem : sessionCartItems) {
			if (cItem.getProduct().getProductId() == cartItem.getProduct().getProductId()) {
				cItem.setQuantity(cartItem.getQuantity() + cItem.getQuantity());
				newCItem = false;
				return;
			}
		}
		if (newCItem) {
			sessionCartItems.add(cartItem);
		}
	}

	@RequestMapping(value = "/removeSessionCartItem/{prodId}", method = RequestMethod.GET)
	public String deleteUserCartItem(@PathVariable String prodId, HttpServletRequest httpServletRequest) {
		System.out.println("inside deleteUserCartItem POST prodId " + prodId);
		List<CartItem> sessionCartItems = new CopyOnWriteArrayList<CartItem>();
		sessionCartItems = (List<CartItem>) httpServletRequest.getSession().getAttribute("CARTITEMS_SESSION");
		Iterator<CartItem> it = sessionCartItems.iterator();
		
		while (it.hasNext()) {
			CartItem sessionItem = it.next();
			System.out.println(" sessionItem Id " + sessionItem.getId());
			if (sessionItem.getProduct().getProductId() == Integer.parseInt(prodId)) {
				//it.remove();
				sessionCartItems.remove(sessionItem);
				httpServletRequest.getSession().setAttribute("CARTITEMS_SESSION", sessionCartItems);
			}
		}
		
		/*
		 * List<CartItem> listOfRemovedItems = (List<CartItem>)
		 * httpServletRequest.getSession() .getAttribute("REMOVED_CARTITEMS_SESSION");
		 * if (listOfRemovedItems == null) { listOfRemovedItems = new
		 * ArrayList<CartItem>();
		 * httpServletRequest.getSession().setAttribute("REMOVED_CARTITEMS_SESSION",
		 * listOfRemovedItems); } CartItem rCItem =
		 * removeCartItemFromCARTITEMS_SESSION(Integer.parseInt(prodId),
		 * httpServletRequest); System.out.println(" rCItem  id" + rCItem.getId()); if
		 * (rCItem.getId() != 0) { listOfRemovedItems.add(rCItem); }
		 * httpServletRequest.getSession().setAttribute("REMOVED_CARTITEMS_SESSION",
		 * listOfRemovedItems); System.out.println("REMOVED_CARTITEMS_SESSION" +
		 * listOfRemovedItems);
		 * removeCartItemFromCARTITEMS_SESSION(Integer.parseInt(prodId),
		 * httpServletRequest);
		 * 
		 * // cartItemServiceImpl.deleteCartItemByCartItemId(Integer.parseInt(id));
		 */		return "redirect:/user/cart";
	}

	public CartItem removeCartItemFromCARTITEMS_SESSION(int prodId, HttpServletRequest httpServletRequest) {
		List<CartItem> sessionCartItems = (List<CartItem>) httpServletRequest.getSession()
				.getAttribute("CARTITEMS_SESSION");
		for (CartItem cItem : sessionCartItems) {
			System.out.println("cart cItem Id " + cItem.getId());
			if (cItem.getProduct().getProductId() == prodId) {
				sessionCartItems.remove(cItem);
				httpServletRequest.getSession().setAttribute("CARTITEMS_SESSION", sessionCartItems);
				return cItem;
			}
		}
		return new CartItem();
	}

	/*
	 * @RequestMapping(value = "/placeOrder", method = RequestMethod.GET) public
	 * String placeOrderPage() { System.out.println("inside placeOrderPage  GET");
	 * return "order"; }
	 */

	/*
	 * @RequestMapping(value = "/saveOrder", method = RequestMethod.POST) public
	 * String saveOrder(@ModelAttribute("order") Order order, BindingResult
	 * bindingResult, Model model) { System.out.println("inside saveOrder POST" +
	 * order); return "redirect: /userAccount"; }
	 */
}
