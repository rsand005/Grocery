package com.DailyGroceryShop.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.DailyGroceryShop.domain.Cart;
import com.DailyGroceryShop.domain.CartItem;
import com.DailyGroceryShop.domain.Category;
import com.DailyGroceryShop.domain.ProdInventory;
import com.DailyGroceryShop.domain.Product;
import com.DailyGroceryShop.domain.User;
import com.DailyGroceryShop.service.CartItemServiceImpl;
import com.DailyGroceryShop.service.CartServiceImpl;
import com.DailyGroceryShop.service.CategoryServiceImpl;
import com.DailyGroceryShop.service.ProdInventoryServiceImpl;
import com.DailyGroceryShop.service.ProductServiceImpl;
import com.DailyGroceryShop.service.UserServiceImpl;

@Controller
@SessionAttributes("CARTITEMS_SESSION")
public class HomeController {

	@Autowired
	ProductServiceImpl productServiceImpl;

	@Autowired
	CategoryServiceImpl categoryServiceImpl;

	@Autowired
	ProdInventoryServiceImpl prodInventoryServiceImpl;

	@Autowired
	CartItemServiceImpl cartItemServiceImpl;

	@Autowired
	CartServiceImpl cartServiceImpl;

	@Autowired
	UserServiceImpl userServiceImpl;

	List<Product> prodList = new ArrayList<Product>();
	List<Category> listCategory = new ArrayList<Category>();
	User currLoginUser;

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String errorPage() {
		return "error";
	}

	@RequestMapping(value = "/login")
	public String loginForm(@RequestParam(required = false) String logout, @RequestParam(required = false) String error,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Principal principal,
			Model model) {
		System.out.println("inside loginForm() POST ");
		String message = "";
		if (error != null) {
			message = "Your credentials are invalid.";
		}
		if (logout != null) {
			System.out.println("Inside logout  if(logout != null)  ");
			afterLogoutUpdatingDBFromSession(httpServletRequest, principal);
			/*
			 * List<CartItem> listCartItems = (List<CartItem>)
			 * httpServletRequest.getSession() .getAttribute("CARTITEMS_SESSION");
			 * System.out.println("Inside logoutPage() listCartItems  " + listCartItems); if
			 * (listCartItems != null) { List<CartItem> cartItems =
			 * cartItemServiceImpl.findAndUpdateCartItemQty(listCartItems); currLoginUser =
			 * userServiceImpl.findUserByUserName(principal.getName()); //Cart cart = new
			 * Cart(); //cart.setListOfCartItems(cartItems); //cart.setUser(currLoginUser);
			 * System.out.println("Inside logoutPage()  saveAndUpdateCart " );
			 * cartServiceImpl.saveAndUpdateCart(currLoginUser.getUserId(), cartItems);
			 */
			//}
			// httpServletRequest.getSession().invalidate();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, auth);
			}
			message = "Logout";
			return "login";
		}
		/*
		 * List<CartItem> sCartItems = (List<CartItem>)
		 * httpServletRequest.getAttribute("CARTITEMS_SESSION"); if (sCartItems == null)
		 * { System.out.println(" if(sCartItems == null)  sCartItems " + sCartItems);
		 * User currLoginUser = userServiceImpl.findUserByUserName(principal.getName());
		 * System.out.println(" if(sCartItems == null)  currLoginUser " +
		 * currLoginUser); Cart cart =
		 * cartServiceImpl.getCartByUser(currLoginUser.getUserId());
		 * System.out.println(" if(sCartItems == null)  cart " + cart); sCartItems =
		 * cartItemServiceImpl.getAllCartItem(); System.out.
		 * println(" if(sCartItems == null)  cartItemServiceImpl.getAllCartItem() " +
		 * sCartItems); }
		 */
		model.addAttribute("Message", message);
		return "login";
	}

	public void springSession(HttpServletRequest httpServletRequest, User currLoginUser) {
		System.out.println(" springSession() " + currLoginUser);
		List<CartItem> sessionCartItems = (List<CartItem>) httpServletRequest.getSession()
				.getAttribute("CARTITEMS_SESSION");
		if (sessionCartItems == null) {
			sessionCartItems = new CopyOnWriteArrayList<CartItem>();
			httpServletRequest.getSession().setAttribute("CARTITEMS_SESSION", sessionCartItems);
		}
		Cart cart = cartServiceImpl.getCartByUser(currLoginUser.getUserId());
		List<CartItem> listCartItems = cartItemServiceImpl.getAllCartItem();
		sessionCartItems.addAll(listCartItems);
		httpServletRequest.getSession().setAttribute("CARTITEMS_SESSION", sessionCartItems);

	}

	
	public String afterLogoutUpdatingDBFromSession(HttpServletRequest request, Principal principal) {
		System.out.println("Inside logoutPage()   ");
		List<CartItem> listCartItems = (List<CartItem>) request.getSession().getAttribute("CARTITEMS_SESSION");
		List<CartItem> listOfRemovedItems = (List<CartItem>) request.getSession().getAttribute("REMOVED_CARTITEMS_SESSION"); 
		System.out.println("Inside logoutPage() listCartItems  " + listCartItems);
		System.out.println("Inside logoutPage() listOfRemovedItems  " + listOfRemovedItems);
		
		/*
		 * if(listOfRemovedItems != null && !listOfRemovedItems.isEmpty()) {
		 * cartItemServiceImpl.deleteCartItemByCartItemId(listOfRemovedItems); }
		 */
		 
		if (listCartItems != null && !listCartItems.isEmpty()) {
			currLoginUser = userServiceImpl.findUserByUserName(principal.getName());
			//cartServiceImpl.deleteCartByUser(currLoginUser);
			//List<CartItem> cartItems = cartItemServiceImpl.findAndUpdateCartItemQty(listCartItems);
			//System.out.println("cartItems " + cartItems);
			//currLoginUser = userServiceImpl.findUserByUserName(principal.getName());
			Cart cart = cartServiceImpl.getCartByUser(currLoginUser.getUserId());
			//Cart cart = new Cart();
			cart.setListOfCartItems(listCartItems);
			//cart.setUser(currLoginUser);
			//System.out.println("Inside logoutPage()   cart created " + cart);
			//cartServiceImpl.saveAndUpdateCart(currLoginUser, cartItems);
			cartServiceImpl.saveCart(cart);
		}else {
			currLoginUser = userServiceImpl.findUserByUserName(principal.getName());
			Cart cart = cartServiceImpl.getCartByUser(currLoginUser.getUserId());
			//Iterator<CartItem> itCartItem = (Iterator<CartItem>) cart.getListOfCartItems();
			cartItemServiceImpl.deleteCartItemByCartItemId(cart.getListOfCartItems());
			cart.setListOfCartItems(listCartItems);
			//cart.getListOfCartItems().removeAll(cart.getListOfCartItems());
			cartServiceImpl.saveCart(cart);
		}
		request.getSession().invalidate();
		return "login";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homePage(@ModelAttribute("cartItem") CartItem cartItem, BindingResult bindingResult, Model model) {

		// System.out.println("Inside HomeController homePage() ");
		prodList = productServiceImpl.listOfProducts();
		// System.out.println("prodList " + prodList);
		listCategory = categoryServiceImpl.getListOfCategory();
		model.addAttribute("listOfProducts", prodList);
		model.addAttribute("listOfCategory", listCategory);
		model.addAttribute("cartItem", cartItem);
		System.out.println("Calling openCSVReader() ");

		return "home";
	}

	@RequestMapping(value = "/findProduct", method = RequestMethod.POST)
	public String findProduct(@RequestParam("searchProd") String searchProd, @ModelAttribute("cartItem") CartItem cartItem, BindingResult bindingResult, ModelMap model) {
		System.out.println("user is searching Product by:  " + searchProd);
		Long categoryId = null;
		for (Category category : listCategory) {
			if (searchProd.equals(category.getType())) {
				categoryId = category.getId();
				System.out.println("categoryId " + categoryId);
			}
		}
		List<Product> prodLists = productServiceImpl.findProductByProdNameOrByCategory(searchProd, categoryId);
		model.addAttribute("listOfProducts", prodLists);
		return "home";
	}

	@RequestMapping(value = "/invalidate/session", method = RequestMethod.POST)
	public String destorySession(HttpServletRequest request) {
		// invalidate the session, this will clear the data from configured database
		// (MySQL).
		// request.getSession().invalidate();
		return "home";
	}
}
