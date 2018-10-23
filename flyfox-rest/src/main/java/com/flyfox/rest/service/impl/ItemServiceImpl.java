package com.flyfox.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.flyfox.common.pojo.FlyfoxResult;
import com.flyfox.common.utils.JsonUtils;
import com.flyfox.mapper.ItemDescMapper;
import com.flyfox.mapper.ItemMapper;
import com.flyfox.mapper.ItemParamItemMapper;
import com.flyfox.pojo.Item;
import com.flyfox.pojo.ItemDesc;
import com.flyfox.pojo.ItemParamItem;
import com.flyfox.pojo.ItemParamItemExample;
import com.flyfox.pojo.ItemParamItemExample.Criteria;
import com.flyfox.rest.dao.JedisClient;
import com.flyfox.rest.service.ItemService;

/**
 * 商品信息管理Service
 * <p>Title: ItemServiceImpl</p>
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private ItemDescMapper itemDescMapper;
	
	@Autowired
	private ItemParamItemMapper itemParamItemMapper;
	
	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;
	@Value("${REDIS_ITEM_EXPIRE}")
	private Integer REDIS_ITEM_EXPIRE;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Override
	public FlyfoxResult getItemBaseInfo(long itemId) {
		try {
			//添加缓存逻辑
			//从缓存中取商品信息，商品id对应的信息
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":base");
			//判断是否有值
			if (!StringUtils.isBlank(json)) {
				//把json转换成java对象
				Item item = JsonUtils.jsonToPojo(json, Item.class);
				return FlyfoxResult.ok(item);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//根据商品id查询商品信息
		Item item = itemMapper.selectByPrimaryKey(itemId);
		//使用FlyfoxResult包装一下
		try {
			//把商品信息写入缓存
			jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":base", JsonUtils.objectToJson(item));
			//设置key的有效期
			jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":base", REDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FlyfoxResult.ok(item);
	}

	@Override
	public FlyfoxResult getItemDesc(long itemId) {
		//添加缓存
		try {
			//添加缓存逻辑
			//从缓存中取商品信息，商品id对应的信息
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":desc");
			//判断是否有值
			if (!StringUtils.isBlank(json)) {
				//把json转换成java对象
				ItemDesc itemDesc = JsonUtils.jsonToPojo(json, ItemDesc.class);
				return FlyfoxResult.ok(itemDesc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//创建查询条件
		ItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		
		try {
			//把商品信息写入缓存
			jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":desc", JsonUtils.objectToJson(itemDesc));
			//设置key的有效期
			jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":desc", REDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return FlyfoxResult.ok(itemDesc);
	}

	@Override
	public FlyfoxResult getItemParam(long itemId) {
		//添加缓存
		try {
			//添加缓存逻辑
			//从缓存中取商品信息，商品id对应的信息
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":param");
			//判断是否有值
			if (!StringUtils.isBlank(json)) {
				//把json转换成java对象
				ItemParamItem paramItem = JsonUtils.jsonToPojo(json, ItemParamItem.class);
				return FlyfoxResult.ok(paramItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//根据商品id查询规格参数
		//设置查询条件
		ItemParamItemExample example = new ItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		//执行查询
		List<ItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		if (list != null && list.size()>0) {
			ItemParamItem paramItem = list.get(0);
			try {
				//把商品信息写入缓存
				jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":param", JsonUtils.objectToJson(paramItem));
				//设置key的有效期
				jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":param", REDIS_ITEM_EXPIRE);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return FlyfoxResult.ok(paramItem);
		}
		return FlyfoxResult.build(400, "无此商品规格");
	}

}
