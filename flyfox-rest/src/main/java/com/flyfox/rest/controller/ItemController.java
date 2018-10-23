package com.flyfox.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flyfox.common.pojo.FlyfoxResult;
import com.flyfox.rest.service.ItemService;

/**
 * 商品信息Controller
 * <p>Title: ItemController</p>
 */
@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/info/{itemId}")
	@ResponseBody
	public FlyfoxResult getItemBaseInfo(@PathVariable Long itemId) {
		FlyfoxResult result = itemService.getItemBaseInfo(itemId);
		return result;
	}
	
	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	public FlyfoxResult getItemDesc(@PathVariable Long itemId) {
		FlyfoxResult result = itemService.getItemDesc(itemId);
		return result;
	}
	
	@RequestMapping("/param/{itemId}")
	@ResponseBody
	public FlyfoxResult getItemParam(@PathVariable Long itemId) {
		FlyfoxResult result = itemService.getItemParam(itemId);
		return result;
	}
}
