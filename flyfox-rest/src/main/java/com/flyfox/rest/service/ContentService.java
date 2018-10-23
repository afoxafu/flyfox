package com.flyfox.rest.service;

import java.util.List;

import com.flyfox.pojo.Content;

public interface ContentService {

	List<Content> getContentList(long contentCid);
}
