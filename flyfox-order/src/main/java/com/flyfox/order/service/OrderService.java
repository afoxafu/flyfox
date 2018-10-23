package com.flyfox.order.service;

import java.util.List;

import com.flyfox.common.pojo.FlyfoxResult;
import com.flyfox.pojo.Order;
import com.flyfox.pojo.OrderItem;
import com.flyfox.pojo.OrderShipping;

public interface OrderService {

	FlyfoxResult createOrder(Order order, List<OrderItem> itemList, OrderShipping orderShipping);
}
