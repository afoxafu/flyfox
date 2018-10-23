package com.flyfox.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flyfox.common.pojo.EUTreeNode;
import com.flyfox.mapper.ItemCatMapper;
import com.flyfox.pojo.ItemCat;
import com.flyfox.pojo.ItemCatExample;
import com.flyfox.pojo.ItemCatExample.Criteria;
import com.flyfox.service.ItemCatService;

/**
 * 商品分类管理
 * <p>Title: ItemCatServiceImpl</p>
 * <p>Description: </p>
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private ItemCatMapper itemCatMapper;
	@Override
	public List<ItemCat> getItemCatList(Long parentId) throws Exception {
		
		ItemCatExample example = new ItemCatExample();
		//设置查询条件
		Criteria criteria = example.createCriteria();
		//根据parentid查询子节点
		criteria.andParentIdEqualTo(parentId);
		//返回子节点列表
		List<ItemCat> list = itemCatMapper.selectByExample(example);
		return list;
	}



}
