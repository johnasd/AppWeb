package com.appweb.controllers;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;

import com.appweb.domain.Product;
import com.appweb.repositories.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class IndexController {
	
	@Autowired
	private ProductRepository productRepository;
	
	
	@RequestMapping(value = "" , method = RequestMethod.GET , produces  = "application/json;charset=UTF-8" )
	@ResponseBody
	public String index(HttpServletRequest httpServletRequest) {
		Gson gson = new Gson();
		ObjectMapper objectMapper = new ObjectMapper();
		
		Product productResponse = new Product();
		String jsonString = "";
		List<Product> products = productRepository.findAll();
		JSONObject jsonObject = new JSONObject();
		String objects = gson.toJson(products).toString().replace("[", "");
		
		try {
			System.out.print(">>>>>>>>>>>>>>"+ gson.toJson(objects));
			 jsonObject.put("getProducts", new JSONObject( gson.toJson(objects) ) );
			 productResponse = gson.fromJson(jsonObject.toString(), Product.class);
			 jsonString = objectMapper.writeValueAsString(productResponse);
			
		} catch (JSONException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch ( JsonProcessingException e ) {
			e.printStackTrace();
		}
		
		return jsonString;
	}
	
	@RequestMapping("/pages/products")
	public String product(ModelMap map) {
		List<Product> products = productRepository.findAll();
		
		map.put("theProducts", products);
		return "pages/products";
	}
	
}
