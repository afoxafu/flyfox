package com.flyfox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flyfox.common.pojo.FlyfoxResult;
import com.flyfox.pojo.ItemParam;
import com.flyfox.service.ItemParamService;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public FlyfoxResult getItemParamByCid(@PathVariable Long itemCatId) {
		FlyfoxResult result = itemParamService.getItemParamByCid(itemCatId);
		return result;
	}
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public FlyfoxResult insertItemParam(@PathVariable Long cid, String paramData) {
		//创建pojo对象
		ItemParam itemParam = new ItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		FlyfoxResult result = itemParamService.insertItemParam(itemParam);
		return result;
	}
}
