package com.flyfox.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flyfox.common.pojo.FlyfoxResult;
import com.flyfox.search.service.ItemService;

/**
 * 索引库维护
 * <p>Title: ItemController</p>

 */
@Controller
@RequestMapping("/manager")
public class ItemController {
	
	@Autowired
	private ItemService itemService;

	/**
	 * 导入商品数据到索引库
	 */
	@RequestMapping("/importall")
	@ResponseBody
	public FlyfoxResult importAllItems() {
		FlyfoxResult result = itemService.importAllItems();
		return result;
	}
}
