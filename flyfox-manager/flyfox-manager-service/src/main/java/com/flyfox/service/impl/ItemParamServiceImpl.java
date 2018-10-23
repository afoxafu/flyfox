package com.flyfox.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flyfox.common.pojo.FlyfoxResult;
import com.flyfox.mapper.ItemParamMapper;
import com.flyfox.pojo.ItemParam;
import com.flyfox.pojo.ItemParamExample;
import com.flyfox.pojo.ItemParamExample.Criteria;
import com.flyfox.service.ItemParamService;
@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private ItemParamMapper itemParamMapper;
	@Override
	public FlyfoxResult getItemParamByCid(long cid) {
		ItemParamExample example = new ItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<ItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		//判断是否查询到结果
		if (list != null && list.size() > 0) {
			return FlyfoxResult.ok(list.get(0));
		}
		
		return FlyfoxResult.ok();
	}

	@Override
	public FlyfoxResult insertItemParam(ItemParam itemParam) {
		//补全pojo
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		//插入到规格参数模板表
		itemParamMapper.insert(itemParam);
		return FlyfoxResult.ok();
	}

}
