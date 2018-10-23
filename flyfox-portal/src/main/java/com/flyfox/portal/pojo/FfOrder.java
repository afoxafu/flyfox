package com.flyfox.portal.pojo;

import java.util.List;

import com.flyfox.pojo.Order;
import com.flyfox.pojo.OrderItem;
import com.flyfox.pojo.OrderShipping;

public class FfOrder extends Order {

	private List<OrderItem> orderItems;
	private OrderShipping orderShipping;
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public OrderShipping getOrderShipping() {
		return orderShipping;
	}
	public void setOrderShipping(OrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}
	
	
}
