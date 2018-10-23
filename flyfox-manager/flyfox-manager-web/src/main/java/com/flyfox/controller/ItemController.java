package com.flyfox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flyfox.common.pojo.EUDataGridResult;
import com.flyfox.common.pojo.FlyfoxResult;
import com.flyfox.pojo.Item;
import com.flyfox.service.ItemService;




@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public Item getItemById(@PathVariable Long itemId) {
		Item tbItem = itemService.getItemById(itemId);
		return tbItem;
	}
	@RequestMapping("/item/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows) {
		EUDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}
	
	@RequestMapping(value="/item/save", method=RequestMethod.POST)
	@ResponseBody
	private FlyfoxResult createItem(Item item, String desc, String itemParams) throws Exception {
		FlyfoxResult result = itemService.createItem(item, desc, itemParams);
		return result;
	}
}
