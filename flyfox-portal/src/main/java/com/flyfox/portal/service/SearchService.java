package com.flyfox.portal.service;

import com.flyfox.portal.pojo.SearchResult;

public interface SearchService {

	SearchResult search(String queryString, int page);
}
