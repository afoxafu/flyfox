package com.flyfox.rest.service;

import com.flyfox.common.pojo.FlyfoxResult;

public interface RedisService {

	FlyfoxResult syncContent(long contentCid);
}
