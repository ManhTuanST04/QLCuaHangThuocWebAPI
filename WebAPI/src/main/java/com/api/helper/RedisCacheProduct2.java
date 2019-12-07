package com.api.helper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.api.DAO.ProductDAO;
import com.api.model.CacheTimeModel;
import com.api.model.ProductModel;

public class RedisCacheProduct2 {

	public ProductModel GetListDataFromCache(ProductDAO proDAO, RedisTemplate redisTemp, int idSP){	
		ProductModel proModel = null;
		String KeyMap = String.valueOf(idSP);
		
		if(redisTemp.hasKey("CacheMapData"))  //Nếu đã có Cache1 thì kiểm tra Map lưu trong cache1
		{
			Map<String, ProductModel> map1 = new HashMap<String, ProductModel>();
			map1 = (Map<String, ProductModel>) redisTemp.opsForValue().get("CacheMapData"); //lấy Map lưu trong Cache
			//Xem trong Map đã có lưu key: data cần lấy chưa
			if(map1.get(KeyMap) != null) //nếu có data thì lấy ra lun
			{
				proModel = map1.get(KeyMap);
			}
			//Nếu chưa có thì kiểm tra Cache lưu số lần và thời gian
			else
			{
				if(redisTemp.hasKey("CacheMapTime")) //Nếu đã có cache lưu số lần và thời gian rồi (Cache2) thì kiểm Tra Map lưu trong Cache
				{
					Map<String, CacheTimeModel> map2 = new HashMap<String, CacheTimeModel>();
					map2 = (Map<String, CacheTimeModel>) redisTemp.opsForValue().get("CacheMapTime");
					//Xem trong map2 đã có dữ liệu key: data chưa
					if(map2.get(KeyMap) != null) //Nếu đã có thì lấy ra kiểm tra số lần
					{
						CacheTimeModel cacheTimeModel = map2.get(KeyMap);
						//Nếu số lần nhỏ hơn 5 thì cộng 1 vào số lần
						if(cacheTimeModel.getCount() < 3) 
						{
							int count = cacheTimeModel.getCount();
							count = count + 1;
							cacheTimeModel.setCount(count);
							map2.remove(KeyMap);
							map2.put(KeyMap, cacheTimeModel);
							redisTemp.delete("CacheMapTime");
							redisTemp.opsForValue().set("CacheMapTime", map2);
							redisTemp.expire("CacheMapTime", 1, TimeUnit.HOURS);
							proModel = proDAO.GetProductById(idSP);
						}
						//Nếu số lần lớn hơn hoặc bằng 5 thì kiểm tra thời gian
						else 
						{
							LocalDate dateNow = LocalDate.now();
							LocalTime timeNow = LocalTime.now();
							if(dateNow == cacheTimeModel.getDateCache()) {
								int t1 = timeNow.getHour() * 60 + timeNow.getMinute();
								int t2 = cacheTimeModel.getTimeCache().getHour() * 60 + cacheTimeModel.getTimeCache().getMinute();
								
								if((t1-t2) < 2) {
									map2.remove(KeyMap);
									redisTemp.delete("CacheMapTime");
									redisTemp.opsForValue().set("CacheMapTime", map2);
									redisTemp.expire("CacheMapTime", 1, TimeUnit.HOURS);
									proModel = proDAO.GetProductById(idSP);
									map1.remove(KeyMap);
									map1.put(KeyMap, proModel);
									redisTemp.delete("CacheMapData");
									redisTemp.opsForValue().set("CacheMapData", map1);
									redisTemp.expire("CacheMapData", 5, TimeUnit.MINUTES);
								}
								//Nếu nhiều hơn 2 phút mà có >5 request đến thì
								//Reset Cache2
								else
								{
									proModel = proDAO.GetProductById(idSP);
									cacheTimeModel.setCount(1);
									cacheTimeModel.setDateCache(LocalDate.now());
									cacheTimeModel.setTimeCache(LocalTime.now());
									map2.remove(KeyMap);
									map2.put(KeyMap, cacheTimeModel);
									redisTemp.delete("CacheMapTime");
									redisTemp.opsForValue().set("CacheMapTime", map2);
									redisTemp.expire("CacheMapTime", 1, TimeUnit.HOURS);
								}
								
							}
							else {
								proModel = proDAO.GetProductById(idSP);
								cacheTimeModel.setCount(1);
								cacheTimeModel.setDateCache(LocalDate.now());
								cacheTimeModel.setTimeCache(LocalTime.now());
								map2.remove(KeyMap);
								map2.put(KeyMap, cacheTimeModel);
								redisTemp.delete("CacheMapTime");
								redisTemp.opsForValue().set("CacheMapTime", map2);
								redisTemp.expire("CacheMapTime", 1, TimeUnit.HOURS);
							}
						}
					}
					//Nếu chưa có key:data trong Map2
					else 
					{
						CacheTimeModel cacheTimeModel = new CacheTimeModel(); //Tạo mới CacheTimeModel với Count = 1
						cacheTimeModel.setCount(1);
						cacheTimeModel.setDateCache(LocalDate.now());
						cacheTimeModel.setTimeCache(LocalTime.now());
						map2.put(KeyMap, cacheTimeModel);
						redisTemp.delete("CacheMapTime");
						redisTemp.opsForValue().set("CacheMapTime", map2);
						redisTemp.expire("CacheMapTime", 1, TimeUnit.HOURS);
						proModel = proDAO.GetProductById(idSP);
					}
				}
				//Nếu chưa có cache2 thì tạo cache và lưu map2 vào cache2
				else 				
				{
					Map<String, CacheTimeModel> map2 = new HashMap<String, CacheTimeModel>();
					CacheTimeModel cacheTimeModel = new CacheTimeModel(); //Tạo mới CacheTimeModel với Count = 1
					cacheTimeModel.setCount(1);
					cacheTimeModel.setDateCache(LocalDate.now());
					cacheTimeModel.setTimeCache(LocalTime.now());
					map2.put(KeyMap, cacheTimeModel);
					redisTemp.opsForValue().set("CacheMapTime", map2);
					redisTemp.expire("CacheMapTime", 1, TimeUnit.HOURS);
					proModel = proDAO.GetProductById(idSP);
				}
			}
		}
		//Nếu chưa có cache1 
		else 
		{
			if(redisTemp.hasKey("CacheMapTime")) //Nếu đã có cache lưu số lần và thời gian rồi (Cache2) thì kiểm Tra Map lưu trong Cache
			{
				Map<String, CacheTimeModel> map2 = new HashMap<String, CacheTimeModel>();
				map2 = (Map<String, CacheTimeModel>) redisTemp.opsForValue().get("CacheMapTime");
				//Xem trong map2 đã có dữ liệu key: data chưa
				if(map2.get(KeyMap) != null) //Nếu đã có thì lấy ra kiểm tra số lần
				{
					CacheTimeModel cacheTimeModel = map2.get(KeyMap);
					//Nếu số lần nhỏ hơn 5 thì cộng 1 vào số lần
					if(cacheTimeModel.getCount() < 3) {
						int count = cacheTimeModel.getCount();
						count = count + 1;
						cacheTimeModel.setCount(count);
						map2.remove(KeyMap);
						map2.put(KeyMap, cacheTimeModel);
						redisTemp.delete("CacheMapTime");
						redisTemp.opsForValue().set("CacheMapTime", map2);
						redisTemp.expire("CacheMapTime", 1, TimeUnit.HOURS);
						proModel = proDAO.GetProductById(idSP);
					}
					//Nếu số lần lớn hơn hoặc bằng 5 thì kiểm tra thời gian
					else 
					{
						LocalDate dateNow = LocalDate.now();
						LocalTime timeNow = LocalTime.now();
						LocalDate dateModel = cacheTimeModel.getDateCache();
						if(dateNow.toString().equals(dateModel.toString())) {
							int t1 = timeNow.getHour() * 60 + timeNow.getMinute();
							int t2 = cacheTimeModel.getTimeCache().getHour() * 60 + cacheTimeModel.getTimeCache().getMinute();
							int t = t1 - t2;
							if(t < 5) {
								map2.remove(KeyMap);
								redisTemp.delete("CacheMapTime");
								redisTemp.opsForValue().set("CacheMapTime", map2);
								redisTemp.expire("CacheMapTime", 1, TimeUnit.HOURS);
								proModel = proDAO.GetProductById(idSP);
								//Tạo cache1 và lưu vào
								Map<String, ProductModel> map1 = new HashMap<String, ProductModel>();
								map1.put(KeyMap, proModel);
								redisTemp.opsForValue().set("CacheMapData", map1);
								redisTemp.expire("CacheMapData", 5, TimeUnit.MINUTES);
							}
							//Nếu nhiều hơn 2 phút mà có >5 request đến thì
							//Reset Cache2
							else
							{
								proModel = proDAO.GetProductById(idSP);
								cacheTimeModel.setCount(1);
								cacheTimeModel.setDateCache(LocalDate.now());
								cacheTimeModel.setTimeCache(LocalTime.now());
								map2.remove(KeyMap);
								map2.put(KeyMap, cacheTimeModel);
								redisTemp.delete("CacheMapTime");
								redisTemp.opsForValue().set("CacheMapTime", map2);
								redisTemp.expire("CacheMapTime", 1, TimeUnit.HOURS);
							}
						}
						else {
							proModel = proDAO.GetProductById(idSP);
							cacheTimeModel.setCount(1);
							cacheTimeModel.setDateCache(LocalDate.now());
							cacheTimeModel.setTimeCache(LocalTime.now());
							map2.remove(KeyMap);
							map2.put(KeyMap, cacheTimeModel);
							redisTemp.delete("CacheMapTime");
							redisTemp.opsForValue().set("CacheMapTime", map2);
							redisTemp.expire("CacheMapTime", 1, TimeUnit.HOURS);
						}
					}
				}
				//Nếu chưa có key:data trong Map2
				else 
				{
					CacheTimeModel cacheTimeModel = new CacheTimeModel(); //Tạo mới CacheTimeModel với Count = 1
					cacheTimeModel.setCount(1);
					cacheTimeModel.setDateCache(LocalDate.now());
					cacheTimeModel.setTimeCache(LocalTime.now());
					map2.put(KeyMap, cacheTimeModel);
					redisTemp.delete("CacheMapTime");
					redisTemp.opsForValue().set("CacheMapTime", map2);
					redisTemp.expire("CacheMapTime", 1, TimeUnit.HOURS);
					proModel = proDAO.GetProductById(idSP);
				}
			}
			//Nếu chưa có cache2 thì tạo cache2 và lưu map2 vào cache2
			else 
			{
				Map<String, CacheTimeModel> map2 = new HashMap<String, CacheTimeModel>();
				CacheTimeModel cacheTimeModel = new CacheTimeModel(); //Tạo mới CacheTimeModel với Count = 1
				cacheTimeModel.setCount(1);
				cacheTimeModel.setDateCache(LocalDate.now());
				cacheTimeModel.setTimeCache(LocalTime.now());
				
				map2.put(KeyMap, cacheTimeModel);
				redisTemp.opsForValue().set("CacheMapTime", map2);
				redisTemp.expire("CacheMapTime", 1, TimeUnit.HOURS);
				proModel = proDAO.GetProductById(idSP);
			}
		}
		
		return proModel;
	}
}
