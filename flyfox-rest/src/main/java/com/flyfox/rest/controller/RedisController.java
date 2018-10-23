package com.flyfox.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flyfox.common.pojo.FlyfoxResult;
import com.flyfox.rest.service.RedisService;

/**
 * 缓存同步Controller
 * <p>Title: RedisController</p>
 */
@Controller
@RequestMapping("/cache/sync")
public class RedisController {

	@Autowired
	private RedisService redisService;
	
	@RequestMapping("/content/{contentCid}")
	@ResponseBody
	public FlyfoxResult contentCacheSync(@PathVariable Long contentCid) {
		FlyfoxResult result = redisService.syncContent(contentCid);
		return result;
	}
}
