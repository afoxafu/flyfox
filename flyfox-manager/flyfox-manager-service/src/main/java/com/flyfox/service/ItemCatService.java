package com.flyfox.service;

import java.util.List;

import com.flyfox.common.pojo.EUTreeNode;
import com.flyfox.pojo.ItemCat;

public interface ItemCatService {
	List<ItemCat>  getItemCatList(Long parentId) throws Exception;
}
