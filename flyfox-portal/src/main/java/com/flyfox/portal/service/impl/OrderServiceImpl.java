package com.flyfox.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.flyfox.common.pojo.FlyfoxResult;
import com.flyfox.common.utils.HttpClientUtil;
import com.flyfox.common.utils.JsonUtils;
import com.flyfox.pojo.Order;
import com.flyfox.portal.pojo.FfOrder;
import com.flyfox.portal.service.OrderService;

/**
 * 订单处理Service
 * <p>Title: OrderServiceImpl</p>
 * <p>Description: </p>
 */
@Service
public class OrderServiceImpl implements OrderService {
	
	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;
	@Value("${ORDER_CREATE_URL}")
	private String ORDER_CREATE_URL;
	

	@Override
	public String createOrder(FfOrder order) {
		//调用创建订单服务之前补全用户信息。
		//从cookie中后取TT_TOKEN的内容，根据token调用sso系统的服务根据token换取用户信息。
		
		//调用flyfox-order的服务提交订单。
		String json = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL, JsonUtils.objectToJson(order));
		//把json转换成flyfoxResult
		FlyfoxResult flyfoxResult = FlyfoxResult.format(json);
		if (flyfoxResult.getStatus() == 200) {
			Object orderId = flyfoxResult.getData();
			return orderId.toString();
		}
		return "";
	}

}