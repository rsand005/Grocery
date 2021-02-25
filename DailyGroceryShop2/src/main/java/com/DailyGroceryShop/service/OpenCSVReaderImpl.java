package com.DailyGroceryShop.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.DailyGroceryShop.domain.Category;
import com.DailyGroceryShop.domain.ProdInventory;
import com.DailyGroceryShop.domain.Product;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvValidationException;

@Service
public class OpenCSVReaderImpl implements OpenCSVReader {
    
	
	@Autowired(required = true)
	CategoryServiceImpl categoryServiceImpl;
	
	@Autowired
	ProductServiceImpl productServiceImpl;
	
	@Autowired
	ProdInventoryServiceImpl prodInventoryServiceImpl;

	//private static final String product_CSV_File_Path = "C://DailyGroceryShop/ProductCSVFile.csv";
	private static final String product_CSV_File_Path = "/WEB-INF/temp";
	
	@Override
	public void openCSVReaderBuiildBeans(MultipartFile file) {
			List<Category> listCategory = categoryServiceImpl.getListOfCategory();
			List<Product> listOfProcutBean = new ArrayList<Product>();
			CSVParser parser = new CSVParserBuilder().withSeparator(',').withIgnoreQuotations(true).build();
			
			try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
					CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).withCSVParser(parser).build();) 
			{
				
				// Reading records one by one in a string array.
				String[] record;
				while ((record = csvReader.readNext()) != null) {
					Product product = new Product();
					System.out.println("record[0] "+ record[0] + "record[6]  " + record[6]);
					product.setBrand(record[0]);
					LocalDate localDate = LocalDate.parse(record[1]);
					Date date = java.sql.Date.valueOf(localDate);
					product.setDateOfExpiry(date);
					product.setPrice(Float.parseFloat(record[2]));
					product.setProdDescription(record[3]);
					product.setProdName(record[4]);
					product.setQuantity(Integer.parseInt(record[5]));
					for(Category category: listCategory) {
						if(category.getType().equals(record[6])){
							product.setCategory(category);
						}
					}
					float discount  = Float.parseFloat(record[7]);
					ProdInventory prodInventory = prodInventoryServiceImpl.save(new ProdInventory (product.getQuantity() ,discount));
					product.setProductInventory(prodInventory);
					System.out.println("Product bean created " + product);
					productServiceImpl.saveProduct(product);
				}
			} catch (Exception e ) {
				e.printStackTrace();
			}
		}


}
