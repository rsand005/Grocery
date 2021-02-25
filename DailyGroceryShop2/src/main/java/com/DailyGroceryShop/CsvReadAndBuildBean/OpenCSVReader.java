package com.DailyGroceryShop.CsvReadAndBuildBean;

import java.io.IOException;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.DailyGroceryShop.domain.Category;
import com.DailyGroceryShop.domain.ProdInventory;
import com.DailyGroceryShop.domain.Product;
import com.DailyGroceryShop.service.CategoryServiceImpl;
import com.DailyGroceryShop.service.ProdInventoryServiceImpl;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.opencsv.exceptions.CsvValidationException;
/*
 * @Component public class OpenCSVReader {
 * 
 * @Autowired(required = true) CategoryServiceImpl categoryServiceImpl;
 * 
 * @Autowired com.DailyGroceryShop.repository.CategoryRepository
 * CategoryRepository;
 * 
 * @Autowired ProdInventoryServiceImpl prodInventoryServiceImpl;
 * 
 * private static final String product_CSV_File_Path =
 * "C://DailyGroceryShop/ProductCSVFile.csv";
 * 
 * public List<Product> openCSVReader() throws IOException,
 * CsvValidationException { List<Category> listCategory =
 * categoryServiceImpl.getListOfCategory(); //List<Category> listCategory =
 * CategoryRepository.findAll(); List<Product> listOfProcutBean = new
 * ArrayList<Product>(); CSVParser parser = new
 * CSVParserBuilder().withSeparator(',').withIgnoreQuotations(true).build(); try
 * (Reader reader = Files.newBufferedReader(Paths.get(product_CSV_File_Path));
 * CSVReader csvReader = new
 * CSVReaderBuilder(reader).withSkipLines(1).withCSVParser(parser).build();) {
 * // Reading records one by one in a string array. String[] record; while
 * ((record = csvReader.readNext()) != null) { Product product = new Product();
 * product.setBrand(record[0]); LocalDate date = LocalDate.parse(record[1]);
 * product.setDateOfExpiry(date); product.setPrice(Integer.parseInt(record[2]));
 * product.setProdDescription(record[3]); product.setProdName(record[4]);
 * product.setQuantity(Integer.parseInt(record[5])); for(Category category:
 * listCategory) { if(record[6].equals(category.getType())){
 * product.setCategory(category); } } float discount =
 * Integer.parseInt(record[7]); ProdInventory prodInventory =
 * prodInventoryServiceImpl.save(new ProdInventory (product.getQuantity()
 * ,discount)); product.setProductInventory(prodInventory);
 * System.out.println("openCSVReader record " + record[0] + " , " + record[1]);
 * listOfProcutBean.add(product); } return listOfProcutBean; } }
 * 
 * public List<Product> csvToBeanBuilder( Class clazz) throws Exception{
 * System.out.println(" csvToBeanBuilder "); Map<String, String> mapping = new
 * HashMap<String, String>(); mapping.put("prodName", "prodName");
 * mapping.put("prodDescription", "prodDescription"); mapping.put("price",
 * "price"); mapping.put("quantity", "quantity"); mapping.put("dateOfExpiry",
 * "dateOfExpiry"); mapping.put("brand", "brand"); mapping.put("category",
 * "category"); mapping.put("productInventory", "productInventory");
 * List<Product> listOfProductBeans = new ArrayList<Product>();
 * HeaderColumnNameTranslateMappingStrategy<Product> strategy = new
 * HeaderColumnNameTranslateMappingStrategy<Product>();
 * strategy.setType(Product.class); strategy.setColumnMapping(mapping);
 * List<Product> csvToBean = null; try ( Reader reader =
 * Files.newBufferedReader(Paths.get(product_CSV_File_Path)); ){
 * System.out.println("csvToBeanBuilder product inside try block" ); csvToBean =
 * new CsvToBeanBuilder(reader).withSeparator(',').withIgnoreQuotations(true).
 * withMappingStrategy(strategy).withSkipLines(1).withIgnoreLeadingWhiteSpace(
 * true).build().parse();
 * 
 * System.out.
 * println("csvToBeanBuilder product inside try block csvToBean.size() " +
 * csvToBean.size()); } Iterator<Product> myProdIterator = csvToBean.iterator();
 * 
 * for(Product product : csvToBean) {
 * System.out.println("csvToBeanBuilder product " + product.getProdName() +
 * " , "+ product.getBrand()); }
 * 
 * 
 * while(myProdIterator.hasNext()) { Product product = myProdIterator.next();
 * System.out.println("csvToBeanBuilder product " + product.getProdName() +
 * " , "+ product.getBrand()); System.out.println(".........");
 * listOfProductBeans.add(product); }
 * 
 * return listOfProductBeans;
 * 
 * 
 * } }
 */