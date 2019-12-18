package com.api.controller;
import java.sql.Date;
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
import com.api.model.BaoCaoDonHangModel;
import com.api.model.CacheTimeModel;
import com.api.model.NhaCungCapModel;
import com.api.model.ProductModel;
import com.api.model.UserModel;

@RestController
public class BaoCaoController {
	@Autowired
	BaoCaoDAO dao;

	@GetMapping(value = "/api/baocao/bcdh")
	public BaoCaoDonHangModel BaoCaoDonHang(Date tuNgay, Date denNgay, int tinhTrangDon) {
		BaoCaoDonHangModel model = dao.BaoCaoDonHang(tuNgay, denNgay, tinhTrangDon);
		return model;
	}
}
