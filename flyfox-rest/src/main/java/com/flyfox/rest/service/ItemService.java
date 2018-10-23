package com.flyfox.rest.service;

import com.flyfox.common.pojo.FlyfoxResult;

public interface ItemService {

	FlyfoxResult getItemBaseInfo(long itemId);
	FlyfoxResult getItemDesc(long itemId);
	FlyfoxResult getItemParam(long itemId);
}
