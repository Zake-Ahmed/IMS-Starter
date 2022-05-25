package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

public class OrderController  implements CrudController<Order>{
	

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private Utils utils;
	
	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}
	
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order);
		}
		
		return orders;
	}

	@Override
	public Order create() {
		LOGGER.info("Please enter the customer ID ");
		Long customerID = utils.getLong();
		Order newOrder = orderDAO.create(new Order(customerID)); 
//		Order newOrder = new Order(customerID);
//		System.out.println(newOrder);
//		LOGGER.info("Order initialised");
//		LOGGER.info("Please enter the ID of the inital product you'd like to add");
//		Long productID = utils.getLong();
//		Order addedOrder = orderDAO.addProduct(newOrder.getId(), productID);
//		LOGGER.info("Product added to Order");
		return newOrder;
	}
	@Override
	public Order update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		LOGGER.info()
		
		
		return null;
	}
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the item you would like to delete");
		Long id = utils.getLong();
		return orderDAO.delete(id);

	}

}
