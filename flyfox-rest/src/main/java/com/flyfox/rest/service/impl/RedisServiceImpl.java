package com.flyfox.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.flyfox.common.pojo.FlyfoxResult;
import com.flyfox.common.utils.ExceptionUtil;
import com.flyfox.rest.dao.JedisClient;
import com.flyfox.rest.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private JedisClient jedisClient;
	
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;
	
	@Override
	public FlyfoxResult syncContent(long contentCid) {
		try {
			jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, contentCid + "");
		} catch (Exception e) {
			e.printStackTrace();
			return FlyfoxResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return FlyfoxResult.ok();
	}

}
