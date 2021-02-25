package com.DailyGroceryShop.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.DailyGroceryShop.dao.ProdInventoryDao;
import com.DailyGroceryShop.dao.ProdInventoryDaoImpl;
import com.DailyGroceryShop.domain.ProdInventory;
import com.DailyGroceryShop.domain.Product;
import com.DailyGroceryShop.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProdInventoryServiceImpl prodInventoryServiceImpl;
	
	@Autowired
	ProdInventoryDao prodInventoryDao;
	
	@Override
	public void saveProduct(Product product) {
		System.out.println("inside ProductServiceImpl saveProduct()");
		productRepository.save(product);
		
	}

	@Override
	public List<Product> listOfProducts() {
		//List<Product> listProducts = productRepository.findAll();
		List<Product> listProducts = (List<Product>) productRepository.findAll();
		return listProducts;
	}

	@Override
	public void deleteProductById(int productId) {
		productRepository.deleteById(productId);
		
	}

	@Override
	public Product findProductByProductId(int productId) {
		return productRepository.findProductByProductId(productId);
	}
	
	
	@Override
	public void updateProduct(Product product) {
		
		 System.out.println("ProductServiceImpl updateProduct product.getProductId() " + product.getProductId());
		
		//productRepository.updateProductByProductId(product.getPrice(), product.getProdName(), product.getDiscount(),product.getQuantity(),  product.getProductId());
		
		  int id = product.getProductId();
		  System.out.println("ProductServiceImpl product.getProductId() " + id );
		  System.out.println("ProductServiceImpl product.getProductInventory() " + product.getProductInventory() );
		  Product updateProd = productRepository.findById(id).get();
		  updateProd.setProdName(product.getProdName());
		  updateProd.setProdDescription(product.getProdDescription());
		  updateProd.setPrice(product.getPrice());
		  updateProd.setBrand(product.getBrand());
		  updateProd.setDateOfExpiry(product.getDateOfExpiry());
		  updateProd.setImage(product.getImage()); 
		  
		  List<ProdInventory> prodInventoryList = new ArrayList<ProdInventory>();
		  prodInventoryList.add(product.getProductInventory());
		  prodInventoryDao.updateProdInvetoryBatch(prodInventoryList);	  
		  productRepository.save(updateProd);
		 
	}

	@Override
	public List<Product> findProductByProdName(String prodName) {
		List<Product> listOfProduct = productRepository.findProductByProdName(prodName);
		System.out.println("ProductServiceImpl findProductByProdName items: " + listOfProduct.size());
		return listOfProduct;
	}
	
	@Override
	public List<Product> findProductByCategory(Long categoryId) {
		List<Product> listOfProductC= productRepository.findProductByCategory(categoryId);
		System.out.println("ProductServiceImpl findProductByCategoryId items: " + listOfProductC.size());
		return listOfProductC;
	}

	@Override
	public List<Product> findProductByProdNameOrByCategory(String prodName, Long category) {
		List<Product> listOfProduct= productRepository.findProductByProdNameOrByCategory(prodName, category);
		System.out.println("ProductServiceImpl ProdNameOrByCategory items: " + listOfProduct.size());
		return listOfProduct;
	}

}
