package com.restaurantdemo.restaurantdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.restaurantdemo.restaurantdemo.model.RestaurantModel;
import com.restaurantdemo.restaurantdemo.service.serviceImpl.RestaurantServiceImpl;

@Controller
public class RestaurantController {
	
	@Autowired
	private RestaurantServiceImpl resServiceImpl;
	
		@RequestMapping(value = "/menu/{tbno}", method = RequestMethod.GET)
		public ModelAndView addMenu(@PathVariable (name = "tbno") String tbNo){
			int table = Integer.parseInt(tbNo);
			List<RestaurantModel> menuList = resServiceImpl.selectAllData();
			List<RestaurantModel> orderList = resServiceImpl.orderList(table);
			ModelAndView model = new ModelAndView();
			model.setViewName("menu");
			model.addObject("menuList", menuList);
			model.addObject("orderList", orderList);
			model.addObject("tableNumber", table);
			return model;
			
		}
		@RequestMapping(value = "/orderFood/{id}", method = RequestMethod.GET)
		public ModelAndView foodOrder(@PathVariable (name = "id") String foodId, @ModelAttribute RestaurantModel resModel) {
			int fId = Integer.parseInt(foodId);
			String tbNo = String.valueOf(resModel.getTbId());
			resServiceImpl.foodOrder(fId, resModel);
			ModelAndView model = new ModelAndView();
			model.setViewName("redirect:/menu/" + tbNo);
			return model;
			
		}
		@RequestMapping(value = "/orderDrink/{id}", method = RequestMethod.GET)
		public ModelAndView drinkOrder(@PathVariable (name = "id") String drinkId, @ModelAttribute RestaurantModel resModel) {
			int dId = Integer.parseInt(drinkId);
			String tbNo = String.valueOf(resModel.getTbId());
			resServiceImpl.drinkOrder(dId, resModel);
			ModelAndView model = new ModelAndView();
			model.setViewName("redirect:/menu/" + tbNo);
			return model;
			
		}
		@RequestMapping(value = "/decreaseOrder/{tableId}", method = RequestMethod.GET)
		public ModelAndView decreaseOrder (@PathVariable (name = "tableId") String tbId, @ModelAttribute RestaurantModel resModel) {
			int desOrder = Integer.parseInt(tbId);
			resServiceImpl.orderDecrease(desOrder, resModel);
			ModelAndView model = new ModelAndView();
			model.setViewName("redirect:/menu/" + tbId);
			return model;
			
		}
		
		@RequestMapping(value = "/cancelOrder/{tableId}", method = RequestMethod.GET)
		public ModelAndView cancelOrder (@PathVariable (name = "tableId") String tbId, @ModelAttribute RestaurantModel resModel) {
			int cancelOrder = Integer.parseInt(tbId);
			resServiceImpl.orderCancel(cancelOrder, resModel);
			ModelAndView model = new ModelAndView();
			model.setViewName("redirect:/menu/" + tbId);
			return model;
			
		}
		@RequestMapping(value = "/showcheck/{id}", method = RequestMethod.GET)
		public ModelAndView showCheck(@PathVariable (name = "id") String tbId) {
			int check = Integer.parseInt(tbId);
			List<RestaurantModel> checkList = resServiceImpl.showCheck(check);
			ModelAndView model = new ModelAndView();
			model.setViewName("check");
			model.addObject("checkList", checkList);
			return model;
			
		}
		@RequestMapping(value = "/mainTables/{id}", method = RequestMethod.GET)
		public ModelAndView mainTable(@PathVariable (name = "id") String tbId) {
			int delete = Integer.parseInt(tbId);
			System.out.println(delete);
			resServiceImpl.deleteAll(delete);
			ModelAndView model = new ModelAndView();
			model.setViewName("redirect:/index");
			return model;
			
		}
		@RequestMapping(value = "/gototable", method = RequestMethod.GET)
		public ModelAndView tbList() {
			ModelAndView model = new ModelAndView();
			model.setViewName("redirect:/index");
			return model;
			
		}
}
