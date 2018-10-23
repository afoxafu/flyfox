package com.flyfox.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flyfox.common.pojo.FlyfoxResult;
import com.flyfox.common.utils.ExceptionUtil;
import com.flyfox.order.pojo.FfOrder;
import com.flyfox.order.service.OrderService;

/**
 * 订单Controller
 * <p>Title: OrderController</p>
 */
@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public FlyfoxResult createOrder(@RequestBody FfOrder order) {
		try {
			FlyfoxResult result = orderService.createOrder(order, order.getOrderItems(), order.getOrderShipping());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return FlyfoxResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}
