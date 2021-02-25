package com.DailyGroceryShop.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.DailyGroceryShop.domain.Product;

@Component
public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Product product = (Product) target;
		System.out.println("ProductValidator "+  product);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "prodName", "product.prodName.empty", "You must enter product name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "prodDescription", "product.prodDescription.empty", "You must enter product description");
		
		if(product.getCategory() == null) {
			errors.rejectValue("category", "product.category", "Please select a product category.");
		}
	}

}
