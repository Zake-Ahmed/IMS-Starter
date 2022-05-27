package com.qa.ims.persistence.domain;


import java.util.List;

public class Order {
	
	private long id;
	private long customerID;
	private List<Item> product;
	private double cost;
	
	public Order(long customerID, List<Item> product) {
		super();
		this.customerID = customerID;
		this.product = product;

	
	}
	public Order(long id, long customerID) {
		super();
		this.id = id;
		this.customerID = customerID;
	}
	public Order(long customerID) {
		super();
		this.customerID = customerID;
	}
	public Order(long id, long customerID, List<Item> product, double cost) {
		super();
		this.id = id;
		this.customerID = customerID;
		this.product = product;
		this.cost = cost;
	}
	public Order(long id, long customerID, List<Item> product) {
		super();
		this.id = id;
		this.customerID = customerID;
		this.product = product;

	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", customerID=" + customerID + ", product=" + product + ", cost=" + cost + "]";
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCustomerID() {
		return customerID;
	}
	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}
	public List<Item> getProduct() {
		return product;
	}
	public void setProduct(List<Item> product) {
		this.product = product;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (customerID ^ (customerID >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (Double.doubleToLongBits(cost) != Double.doubleToLongBits(other.cost))
			return false;
		if (customerID != other.customerID)
			return false;
		if (id != other.id)
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
	
	
	

}
