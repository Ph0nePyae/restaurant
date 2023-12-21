package com.restaurantdemo.restaurantdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.restaurantdemo.restaurantdemo.model.RestaurantModel;
import com.restaurantdemo.restaurantdemo.service.serviceImpl.RestaurantServiceImpl;


@Controller
public class StartController {
	
		@Autowired
		private RestaurantServiceImpl resServiceImpl;
	
		@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
		public ModelAndView start() {
			List<RestaurantModel> tableList = resServiceImpl.tableList();
			ModelAndView model = new ModelAndView();
			model.setViewName("index");
			model.addObject("tableList", tableList);
			return model;	
		}
}
