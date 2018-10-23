package com.flyfox.service;

import com.flyfox.common.pojo.FlyfoxResult;
import com.flyfox.pojo.ItemParam;

public interface ItemParamService {
	FlyfoxResult getItemParamByCid(long cid);
	FlyfoxResult insertItemParam(ItemParam itemParam);
}
