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
import com.api.model.NhaCungCapModel;
import com.api.model.ProductModel;
import com.api.model.UserModel;

@RestController
public class NhaCungCapController {
	@Autowired
	private NhaCungCapDAO dao;
	
	@GetMapping(path = "/api/nhacungcap/dsncc")
	public List<NhaCungCapModel>  DanhSachNCC(){
		List<NhaCungCapModel> res = new ArrayList<NhaCungCapModel>();
		res = dao.DanhSachNCC();
		return res;
	}
	
	
	//Lay theo id
	@GetMapping(path = "/api/nhacungcap/getById")
	public NhaCungCapModel  GetById(int idNCC){
		NhaCungCapModel res = new NhaCungCapModel();
		res = dao.GetNCCById(idNCC);
		return res;
	}
	
	
	@PostMapping(path = "/api/nhacungcap/add")
	public int AddNCC(@RequestBody NhaCungCapModel model) {
		try {
			int res = dao.ThemNhaCungCap(model);	
			return res;
		}
		catch (Exception ex) {
			return 0;
		}
	}
	
	@PostMapping(path = "/api/nhacungcap/edit")
	public int SuaNCC(@RequestBody NhaCungCapModel model) {
		try {
			int res = dao.SuaNhaCungCap(model);	
			return res;
		}
		catch (Exception ex) {
			return 0;
		}
	}
	
	@GetMapping(path = "/api/nhacungcap/delete")
	public int Xoa(int idNCC){
		//NhaCungCapModel res = new NhaCungCapModel();
		int res = dao.XoaNhaCungCap(idNCC);
		return res;
	}
	
}
