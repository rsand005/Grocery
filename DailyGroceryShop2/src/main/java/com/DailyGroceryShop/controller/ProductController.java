package com.DailyGroceryShop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.DailyGroceryShop.domain.Category;
import com.DailyGroceryShop.domain.ProdInventory;
import com.DailyGroceryShop.domain.Product;
import com.DailyGroceryShop.service.CategoryServiceImpl;
import com.DailyGroceryShop.service.OpenCSVReaderImpl;
import com.DailyGroceryShop.service.ProdInventoryServiceImpl;
import com.DailyGroceryShop.service.ProductServiceImpl;
import com.DailyGroceryShop.validator.ProductValidator;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductServiceImpl productServiceImpl;
	
	@Autowired
	CategoryServiceImpl categoryServiceImpl;
	
	@Autowired
	ProdInventoryServiceImpl prodInventoryServiceImpl;
	
	@Autowired(required=true)
	ProductValidator productValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(productValidator);
	}
	
	List<Category> listCategory = new ArrayList<Category>();
	List<Product> prodList =  new ArrayList<Product>();
	
	
	@RequestMapping(value= "", method=RequestMethod.GET)
	public String getProductForm(@ModelAttribute("product") Product product, BindingResult bindingResult, Model model) {
		System.out.println("Inside ProductController  getProductForm()  ");
		prodList = productServiceImpl.listOfProducts();
		listCategory = categoryServiceImpl.getListOfCategory();
		System.out.println("listOfCategory.size()  " + listCategory.size() + " prodList " + prodList.size());
		
		model.addAttribute("listOfProducts", prodList); 
		model.addAttribute("listOfCategory", listCategory); 
		System.out.println("listOfProducts model " + model.toString());
		return "productForm";
	}
		
	@RequestMapping(value="/saveProduct", method= {RequestMethod.POST, RequestMethod.GET})
	public String saveProduct(@Validated @ModelAttribute("product") Product product, BindingResult bindingResult, ModelMap model) {
		System.out.println("Inside ProductController  saveProduct()  ");
		System.out.println(product);
		if(bindingResult.hasErrors()) {
			System.out.println(" bindingResult error " + bindingResult.getObjectName() );
			model.addAttribute("product", product);
			model.addAttribute("listOfProducts", prodList); 
			model.addAttribute("listOfCategory", listCategory); 
			return "productForm";
		}
		System.out.println("ProductController " +  product.getProductInventory().getDiscount());
		ProdInventory prodInventory = prodInventoryServiceImpl.save(new ProdInventory (product.getQuantity() ,product.getProductInventory().getDiscount()));
		product.setProductInventory(prodInventory);
		productServiceImpl.saveProduct(product);
		return "redirect:/product";
	}
	
	@RequestMapping(value="/deleteProduct/{productId}", method=RequestMethod.POST)
	public String deleteProduct(@PathVariable String productId) {
		System.out.println("deleteProduct "+ productId);
		productServiceImpl.deleteProductById(Integer.parseInt(productId));
		return "redirect:/product";
	}
	
	@RequestMapping(value="/updateProduct/{productId}", method=  RequestMethod.GET)
	public String updateProductPage(@PathVariable String productId, Model model) {
		System.out.println("Inside ProductController updateProductPage  " + productId);
		model.addAttribute("product", productServiceImpl.findProductByProductId(Integer.parseInt(productId)));
		return "updateProduct";
	}
	@RequestMapping(value="/updateProduct", method=  {RequestMethod.GET, RequestMethod.POST})
	public String updateProductPage(@Validated @ModelAttribute("product") Product product, BindingResult bindingResult, Model model) {
		System.out.println("updateProduct "+ product.getBrand() + "  product.getProductId() "+product.getProductId());
		//productServiceImpl.findProductByProductId(product.getProductId());
		System.out.println();
		if(bindingResult.hasErrors()) {
			System.out.println(" bindingResult error " + bindingResult.getObjectName() );
			model.addAttribute("product", product);
			model.addAttribute("listOfProducts", prodList); 
			model.addAttribute("listOfCategory", listCategory); 
			return "productForm";
		}
		productServiceImpl.updateProduct(product);
		return "redirect:/product";
	}
	
	
}
