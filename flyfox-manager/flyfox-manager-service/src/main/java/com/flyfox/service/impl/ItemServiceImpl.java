package com.flyfox.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flyfox.common.pojo.EUDataGridResult;
import com.flyfox.common.pojo.FlyfoxResult;
import com.flyfox.common.utils.IDUtils;
import com.flyfox.mapper.ItemDescMapper;
import com.flyfox.mapper.ItemMapper;
import com.flyfox.mapper.ItemParamItemMapper;
import com.flyfox.pojo.Item;
import com.flyfox.pojo.ItemDesc;
import com.flyfox.pojo.ItemExample;
import com.flyfox.pojo.ItemExample.Criteria;
import com.flyfox.pojo.ItemParamItem;
import com.flyfox.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private ItemDescMapper itemDescMapper;
	
	@Autowired
	private ItemParamItemMapper itemParamItemMapper;
	@Override
	public Item getItemById(long itemId) {
		
		//Item item = itemMapper.selectByPrimaryKey(itemId);
		//添加查询条件
		ItemExample example = new ItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<Item> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			Item item = list.get(0);
			return item;
		}
		return null;
	}
	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		//查询商品列表
				ItemExample example = new ItemExample();
				//分页处理
				PageHelper.startPage(page, rows);
				List<Item> list = itemMapper.selectByExample(example);
				//创建一个返回值对象
				EUDataGridResult result = new EUDataGridResult();
				result.setRows(list);
				//取记录总条数
				PageInfo<Item> pageInfo = new PageInfo<>(list);
				result.setTotal(pageInfo.getTotal());
				return result;
	}

	@Override
	public FlyfoxResult createItem(Item item, String desc, String itemParam) throws Exception {
		//item补全
		//生成商品ID
		Long itemId = IDUtils.genItemId();
		item.setId(itemId);
		// '商品状态，1-正常，2-下架，3-删除',
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		//插入到数据库
		itemMapper.insert(item);
		//添加商品描述信息
		FlyfoxResult result = insertItemDesc(itemId, desc);
		if (result.getStatus() != 200) {
			throw new Exception();
		}
		//添加规格参数
//		result = insertItemParamItem(itemId, itemParam);
//		if (result.getStatus() != 200) {
//			throw new Exception();
//		}
		return FlyfoxResult.ok();
	}
	/**
	 * 添加商品描述
	 * <p>Title: insertItemDesc</p>
	 * <p>Description: </p>
	 * @param desc
	 */
	private FlyfoxResult insertItemDesc(Long itemId, String desc) {
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDescMapper.insert(itemDesc);
		return FlyfoxResult.ok();
	}
	
	/**
	 * 添加规格参数
	 * <p>Title: insertItemParamItem</p>
	 * <p>Description: </p>
	 * @param itemId
	 * @param itemParam
	 * @return
	 */
	private FlyfoxResult insertItemParamItem(Long itemId, String itemParam) {
		//创建一个pojo
		ItemParamItem itemParamItem = new ItemParamItem();
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParam);
		itemParamItem.setCreated(new Date());
		itemParamItem.setUpdated(new Date());
		//向表中插入数据
		itemParamItemMapper.insert(itemParamItem);
		
		return FlyfoxResult.ok();
		
	}


}
