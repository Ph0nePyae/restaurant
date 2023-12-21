package com.restaurantdemo.restaurantdemo.service;

import java.util.List;

import com.restaurantdemo.restaurantdemo.model.RestaurantModel;



public interface RestaurantService {
	public List<RestaurantModel> tableList();
	public List<RestaurantModel> selectAllData();
	public List<RestaurantModel> orderList(int table);
	public int foodOrder(int fId, RestaurantModel resModel);
	public int drinkOrder(int dId, RestaurantModel resModel);
	public int orderDecrease(int desOrder, RestaurantModel resModel);
	public int orderCancel(int cancelOrder, RestaurantModel resModel);
	public List<RestaurantModel> showCheck(int check);
	public int deleteAll(int delete);
}
