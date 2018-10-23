package com.flyfox.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flyfox.common.pojo.FlyfoxResult;
import com.flyfox.common.utils.ExceptionUtil;
import com.flyfox.pojo.Content;
import com.flyfox.rest.service.ContentService;

/**
 * 内容管理Controller
 * <p>Title: ContentController</p>
 */
@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/content{contentCategoryId}")
	@ResponseBody
	public FlyfoxResult getContentList(@PathVariable Long contentCategoryId) {
		try {
			List<Content> list = contentService.getContentList(contentCategoryId);
			return FlyfoxResult.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
			return FlyfoxResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}