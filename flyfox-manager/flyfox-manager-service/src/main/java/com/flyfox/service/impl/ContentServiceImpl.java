package com.flyfox.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.flyfox.common.pojo.FlyfoxResult;
import com.flyfox.common.utils.HttpClientUtil;
import com.flyfox.mapper.ContentMapper;
import com.flyfox.pojo.Content;
import com.flyfox.service.ContentService;

/**
 * 内容管理
 * <p>Title: ContentServiceImpl</p>
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private ContentMapper  contentMapper;
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_CONTENT_SYNC_URL}")
	private String REST_CONTENT_SYNC_URL;
	
	
	@Override
	public FlyfoxResult insertContent(Content content) {
		//补全pojo内容
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content);
		//添加缓存同步逻辑
		try {
			HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FlyfoxResult.ok();
	}

}
