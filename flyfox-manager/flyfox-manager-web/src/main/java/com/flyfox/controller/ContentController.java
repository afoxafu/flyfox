package com.flyfox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flyfox.common.pojo.FlyfoxResult;
import com.flyfox.pojo.Content;
import com.flyfox.service.ContentService;

/**
 * 内容管理Controller
 * <p>Title: ContentController</p>
 */
@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/save")
	@ResponseBody
	public FlyfoxResult insertContent(Content content) {
		FlyfoxResult result = contentService.insertContent(content);
		return result;
	}
}
