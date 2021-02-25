package com.DailyGroceryShop.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.DailyGroceryShop.domain.FileModel;
import com.DailyGroceryShop.domain.Product;
import com.DailyGroceryShop.service.OpenCSVReaderImpl;

@Controller
@RequestMapping("/csv")
public class CSVController {

	@Autowired
	OpenCSVReaderImpl openCSVReaderImpl;
	
	@Autowired
	ServletContext context;
	
	private static final String product_CSV_File_Path = "C:\\DailyGroceryShop\\src\\main\\webapp\\WEB-INF\\temp";
	
	@RequestMapping(value="/uploadCSV" , method=RequestMethod.GET)
	public String csvUploadPage() {
		System.out.println("inside csvUploadPage GET");
		//FileModel file = new FileModel();
		//ModelAndView modelAndView = new ModelAndView("csvUpload", "command", file);
		return "csvUpload";
	}

	@RequestMapping(value="/uploadCSV",method=RequestMethod.POST)
	public String uploadFile(@RequestParam("file")  MultipartFile file) {
		System.out.println("inside csv uploadFile POST");
		//model.addAttribute("file", file);
		//validate file
		
			System.out.println("There is an error to upload File.");
		
			//parse CSV file to create list of Product Objects in openCSVReaderImpl. 
			System.out.println("Fecting file");
			//MultipartFile mulitipartFile = file.getFile();
			//String uploadPath = context.getRealPath(product_CSV_File_Path); 
			//FileCopyUtils.copy(file.getFile().getBytes(), new File(uploadPath+file.getFile().getOriginalFilename()));
			openCSVReaderImpl.openCSVReaderBuiildBeans(file);
			//model.addAttribute("Products", "Products");
			//model.addAttribute("status", true);
		
		return "csvUpload";
	}

}
