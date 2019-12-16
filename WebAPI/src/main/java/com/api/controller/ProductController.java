package com.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation .RestController;

import com.api.DAO.*;
import com.api.helper.RedisCacheProduct;
import com.api.helper.RedisCacheProduct2;
import com.api.model.CacheTimeModel;
import com.api.model.ProductModel;
import com.api.model.UserModel;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	@Autowired
	private ProductDAO proDAO;
	
	@Autowired
    private RedisTemplate redisTemp;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@GetMapping(value = "/lstpro" , produces = "application/json")
	public List<ProductModel> GetListProduct(){
		List<ProductModel> lstPro = null;
		//RedisCacheProduct rch = new RedisCacheProduct();
		//lstPro = rch.GetListDataFromCache(proDAO, redisTemp, "lstpro");
		lstPro = proDAO.GetListProduct();
		
		return lstPro;
	}
	
	@GetMapping(value = "/productdetail" , produces = "application/json")
	public ProductModel GetProductById(int id){
		//List<ProductModel> lstPro = null;
		RedisCacheProduct2 rch = new RedisCacheProduct2();
		
		//ProductModel pro = rch.GetListDataFromCache(proDAO, redisTemp,id);
		ProductModel pro = proDAO.GetProductById(id);
		
		return pro;
	}
	
	@PostMapping(path = "/addproduct")
	public int AddPro(@RequestBody ProductModel model) {
		try {
			int res = proDAO.AddProduct(model);	
			logger.info("Them san pham");
			return res;
		}
		catch (Exception ex) {
			logger.error(ex.getMessage());
			return 0;
		}
	}
	
	@PostMapping(path = "/updateproduct")
	public int UpdatePro(@RequestBody ProductModel model) {
		try {
			int res = proDAO.UpdateProduct(model);	
			logger.info("Update san pham");
			return res;
		}
		catch (Exception ex) {
			logger.error(ex.getMessage());
			return 0;
		}
	}
}











