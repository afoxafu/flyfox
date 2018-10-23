package com.flyfox.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.flyfox.common.pojo.FlyfoxResult;
import com.flyfox.common.utils.HttpClientUtil;
import com.flyfox.portal.pojo.SearchResult;
import com.flyfox.portal.service.SearchService;

/**
 * 商品搜索Service
 * <p>Title: SearchServiceImpl</p>
 */

@Service
public class SearchServiceImpl implements SearchService {

	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;
	
	@Override
	public SearchResult search(String queryString, int page) {
		// 调用flyfox-search的服务
		//查询参数
		Map<String, String> param = new HashMap<>();
		param.put("q", queryString);
		param.put("page", page + "");
		try {
			//调用服务
			String json = HttpClientUtil.doGet(SEARCH_BASE_URL, param);
			//把字符串转换成java对象
			FlyfoxResult flyfoxResult = FlyfoxResult.formatToPojo(json, SearchResult.class);
			if (flyfoxResult.getStatus() == 200) {
				SearchResult result = (SearchResult) flyfoxResult.getData();
				return result;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
