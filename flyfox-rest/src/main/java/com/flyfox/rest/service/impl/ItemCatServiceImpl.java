package com.flyfox.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flyfox.mapper.ItemCatMapper;
import com.flyfox.pojo.ItemCat;
import com.flyfox.pojo.ItemCatExample;
import com.flyfox.pojo.ItemCatExample.Criteria;
import com.flyfox.rest.pojo.CatNode;
import com.flyfox.rest.pojo.CatResult;
import com.flyfox.rest.service.ItemCatService;


@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private ItemCatMapper itemCatMapper;
	
	@Override
	public CatResult getItemCatList() {
		
		CatResult catResult = new CatResult();
		//查询分类列表
		catResult.setData(getCatList(0));
		return catResult;
	}
	
	/**
	 * 查询分类列表
	 * <p>Title: getCatList</p>
	 * <p>Description: </p>
	 * @param parentId
	 * @return
	 */
	private List<?> getCatList(long parentId) {
		//创建查询条件
		ItemCatExample example = new ItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<ItemCat> list = itemCatMapper.selectByExample(example);
		//返回值list
		List resultList = new ArrayList<>();
		//向list中添加节点
		int count = 0;
		for (ItemCat tbItemCat : list) {
			//判断是否为父节点
			if (tbItemCat.getIsParent()) {
				CatNode catNode = new CatNode();
				if (parentId == 0) {
					catNode.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
				} else {
					catNode.setName(tbItemCat.getName());
				}
				catNode.setUrl("/products/"+tbItemCat.getId()+".html");
				catNode.setItem(getCatList(tbItemCat.getId()));
				
				resultList.add(catNode);
				count ++;
				//第一层只取14条记录
				if (parentId == 0 && count >=14) {
					break;
				}
			//如果是叶子节点
			} else {
				resultList.add("/products/"+tbItemCat.getId()+".html|" + tbItemCat.getName());
			}
		}
		return resultList;
	}
}
