package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTest {
	private final ItemDAO iDAO = new ItemDAO();
	private final OrderDAO DAO = new OrderDAO(iDAO);
	
	
	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data2.sql");
	
	}
	
	
	@Test
	public void testCreated () {
		final Order created = new Order(2L, 1L, new ArrayList<>());
		assertEquals(created, DAO.create(created));
	}
	@Test
	public void testReadAll() {
		List<Order> expected = new ArrayList<>();
		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, "watch", 10D));
		expected.add(new Order(1L, 1L,items,10D));
		assertEquals(expected, DAO.readAll());
	}
	@Test
	public void testReadLatest() {

		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, "watch", 10D));
		Order expected = new Order(1L, 1L,items,10D);
		assertEquals(expected, DAO.readLatest());
	}
	
	@Test
	public void testRead() {
		final long ID = 1L;
		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, "watch", 10D));
		Order expected = new Order(1L, 1L,items,10D);
		assertEquals(expected, DAO.read(ID));
	
	}
	
	@Test
	public void testUpdate() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, "watch", 10D));
		Order expected = new Order(1L, 2L,items,10D);
		assertEquals(expected, DAO.update(expected));

	}
	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}
	
	@Test
	public void addProductTest() {
		Order newOrder= DAO.addProduct(1, 2);
		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, "watch", 10D));
		items.add(new Item(2L, "watch", 10D));
		Order expected = new Order(1L, 1L,items,20D);
		assertEquals(expected, newOrder);
	}
	
	@Test
	public void delProductTest() {
		List<Item> items = new ArrayList<>();
		Order expected = new Order(1L, 1L,items,0);
		assertEquals(expected, DAO.delProduct(1, 1));
	}
	

}
