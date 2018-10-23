package com.flyfox.search.service;

import com.flyfox.search.pojo.SearchResult;

public interface SearchService {

	SearchResult search(String queryString, int page, int rows) throws Exception;
}
