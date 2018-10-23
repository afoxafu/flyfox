package com.flyfox.service;

import com.flyfox.common.pojo.EUDataGridResult;
import com.flyfox.common.pojo.FlyfoxResult;
import com.flyfox.pojo.Item;

public interface ItemService {

	Item getItemById(long itemId);
	EUDataGridResult getItemList(int page, int rows);
	FlyfoxResult createItem(Item item, String desc, String itemParam) throws Exception;
}
