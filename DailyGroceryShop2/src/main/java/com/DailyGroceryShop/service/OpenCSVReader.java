package com.DailyGroceryShop.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.DailyGroceryShop.domain.Product;

public interface OpenCSVReader {
	
	public void openCSVReaderBuiildBeans(MultipartFile file);

}
