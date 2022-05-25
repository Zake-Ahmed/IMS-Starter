package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order> {
	
	public static final Logger LOGGER = LogManager.getLogger();
	private ItemDAO itemDAO;
//	
//	public List<Item> readItems(Long order_id) {
//		try (Connection connection = DBUtils.getInstance().getConnection();
//				Statement statement = connection.createStatement();
//				ResultSet resultSet = statement.executeQuery("SELECT product_id FROM joining WHERE order_id = " + order_id);){
//			List<Long> itemsID = new ArrayList<>();
//		 while(resultSet.next()) {
//			itemsID.add(resultSet.getLong("product_id"));
//			
//		}
//		List<Item> itemsList = new ArrayList<>();
//		for(int i = 0; i<itemsID.size(); i++) {
//			itemsList.add(itemDAO.read(itemsID.get(i)));
//		}
//		return itemsList;
//		}catch (SQLException e) {
//			LOGGER.debug(e);
//			LOGGER.error(e.getMessage());
//		}
//		return null;
//}
	public OrderDAO(ItemDAO itemDAO) {
	super();
	this.itemDAO = itemDAO;
}

	public List<Item> readItems(Long order_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.
						prepareStatement("SELECT product_id FROM joining WHERE order_id = ?");){
			statement.setLong(1, order_id);
			try (ResultSet resultSet = statement.executeQuery();) {
				List<Long> itemsID = new ArrayList<>();
				 while(resultSet.next()) {
					itemsID.add(resultSet.getLong("product_id"));
					
				}
				 List<Item> itemsList = new ArrayList<>();
					for(int i = 0; i<itemsID.size(); i++) {
						itemsList.add(itemDAO.read(itemsID.get(i)));
					}
					return itemsList;
				
			}
		}catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
			
}		
	

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id= resultSet.getLong("Order_id");
		Long customerID = resultSet.getLong("customer_id");
		List<Item> products =readItems(id);
		List<Double> prices = new ArrayList<>();
		Double cost = 0D;
		if(products.size()>=1) {
		
		for (int i=0 ; i<products.size() ; i++) {
			prices.add(products.get(i).getPrice());
			
		}
        Double cost1= prices.stream().reduce((a,b) -> a+b).get();
        
        cost1=Math.round(cost1*100.00)/100.00;
        try(Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE orders SET cost = ? WHERE  order_id= ?");) {
			statement.setDouble(1, cost1);
			statement.setLong(2, id);
			statement.executeUpdate();}
       
        
        return  new Order(id , customerID , products, cost1);
		}else {
	
		return new Order(id , customerID , products, cost);
	}
}
	
	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");) {
			List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}


	public Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY order_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public Order create(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orders(customer_id) VALUES (?)");) {
			statement.setLong(1, order.getCustomerID());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
		
	}
	@Override
	public Order read(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE order_id = ?");) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}



	@Override
	public Order update(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE orders SET customer_id = ? WHERE  order_id= ?");) {
			statement.setLong(1, order.getCustomerID());
			statement.setLong(2, order.getId());
			statement.executeUpdate();
			return read(order.getId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
		
	}

	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE order_id = ?");) {
			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	
	}
	
	public Order addProduct(long order_id , long product_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO joining(order_id, product_id) VALUES (?, ?)");) {
			statement.setLong(1, order_id);
			statement.setLong(2, product_id);
			statement.executeUpdate();
			return read(order_id);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
		
	}
	
	public Order delProduct(long order_id , long product_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("delete from joining where product_id = ? AND order_id = ?");) {
			statement.setLong(1, product_id);
			statement.setLong(2, order_id);
			statement.executeUpdate();
			return read(order_id);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
		
	}
	
	
	
	


	
	

}
