package com.api.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class CacheTimeModel implements Serializable{
	private int Count;
	private LocalDate DateCache;
	private LocalTime TimeCache;

	public CacheTimeModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CacheTimeModel(int count, LocalDate dateCache, LocalTime timeCache) {
		super();
		Count = count;
		DateCache = dateCache;
		TimeCache = timeCache;
	}

	public int getCount() {
		return Count;
	}
	public void setCount(int count) {
		Count = count;
	}
	public LocalDate getDateCache() {
		return DateCache;
	}
	public void setDateCache(LocalDate localDate) {
		DateCache = localDate;
	}
	public LocalTime getTimeCache() {
		return TimeCache;
	}
	public void setTimeCache(LocalTime localTime) {
		TimeCache = localTime;
	}
	
	
}
