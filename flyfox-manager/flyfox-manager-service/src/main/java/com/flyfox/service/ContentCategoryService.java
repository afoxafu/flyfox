package com.flyfox.service;

import java.util.List;

import com.flyfox.common.pojo.FlyfoxResult;
import com.flyfox.common.pojo.EUTreeNode;
import com.flyfox.common.pojo.FlyfoxResult;

public interface ContentCategoryService {

	List<EUTreeNode> getCategoryList(long parentId);
	FlyfoxResult insertContentCategory(long parentId, String name);
}
