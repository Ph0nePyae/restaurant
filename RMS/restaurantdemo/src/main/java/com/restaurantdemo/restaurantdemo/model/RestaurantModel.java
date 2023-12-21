package com.restaurantdemo.restaurantdemo.model;

import lombok.Getter;
import lombok.Setter;

public class RestaurantModel {
	
	@Getter
	@Setter
	private int tbId; // table Id
	
	@Getter
	@Setter
	private int fId; // food Id
	
	@Getter
	@Setter
	private int dId; // drink Id
	
	@Getter
	@Setter
	private String food;
	
	@Getter
	@Setter
	private String drink;
	
	@Getter
	@Setter
	private int price;
	
	@Getter
	@Setter
	private int dprice;
	
	@Getter
	@Setter
	private String tableList;
	
	@Getter
	@Setter
	private int itemNumber;
	
	@Getter
	@Setter
	private int aTotal;
	
	@Getter
	@Setter
	private int total;
	
}

/*"/orderDrink/{dId}"@RequestMapping(value = {"/orderFood/{fId}" , "/orderDrink/{dId}"}, method = RequestMethod.GET)
	public ModelAndView order(@PathVariable (name = "fId") String fId, @PathVariable (name = "dId") String dId) {
		int foodOrder = Integer.parseInt(fId);
		int drinkOrder = Integer.parseInt(dId);
		int result = resServiceImpl.orderFood(foodOrder, drinkOrder);
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/menu");
		model.addObject("orderResult", result);
		return model;
		
	}*/

/*
 * @RequestMapping(value = "/orderFood/{fId}", method = RequestMethod.GET)
	public ModelAndView foodOrder(@PathVariable (name = "fId") String fId) {
		int foodOrder = Integer.parseInt(fId);
		int result = resServiceImpl.orderFood(foodOrder);
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/menu");
		model.addObject("orderResult", result);
		return model;
		
	}*/

/*   
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * @RequestMapping(value = "/orderFood/{fId}", method = RequestMethod.GET)
	public ModelAndView foodOrder(@PathVariable (name = "fId") String fId, @ModelAttribute RestaurantModel resModel) {
		int foodOrder = Integer.parseInt(fId);
		int result = resServiceImpl.orderFood(foodOrder, resModel);
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/menu");
		model.addObject("orderResult", result);
		return model;
		
	}
	
	@RequestMapping(value = "/orderDrink/{dId}", method = RequestMethod.GET)
	public ModelAndView drinkOrder(@PathVariable (name = "dId") String dId) {
		int drinkOrder = Integer.parseInt(dId);
		int result = resServiceImpl.orderDrink(drinkOrder);
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/menu");
		model.addObject("orderResult", result);
		return model;
		
	}
	
	*
	*
	*
	*
	*
	*
	*
	*
	*/

