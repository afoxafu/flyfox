package com.flyfox.search.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flyfox.common.pojo.FlyfoxResult;
import com.flyfox.common.utils.ExceptionUtil;
import com.flyfox.search.mapper.SolrItemMapper;
import com.flyfox.search.pojo.Item;
import com.flyfox.search.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private SolrItemMapper solritemMapper;
	
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public FlyfoxResult importAllItems() {
		try {
			
			//查询商品列表
			List<Item> list = solritemMapper.getItemList();
			//把商品信息写入索引库
			for (Item item : list) {
				//创建一个SolrInputDocument对象
				SolrInputDocument document = new SolrInputDocument();
				document.setField("id", item.getId());
				document.setField("item_title", item.getTitle());
				document.setField("item_sell_point", item.getSell_point());
				document.setField("item_price", item.getPrice());
				document.setField("item_image", item.getImage());
				document.setField("item_category_name", item.getCategory_name());
				document.setField("item_desc", item.getItem_des());
				//写入索引库
				solrServer.add(document);
			}
			//提交修改
			solrServer.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return FlyfoxResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return FlyfoxResult.ok();
	}

}
