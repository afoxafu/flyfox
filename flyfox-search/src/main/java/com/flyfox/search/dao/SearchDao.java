package com.flyfox.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.flyfox.search.pojo.SearchResult;

public interface SearchDao {

	SearchResult search(SolrQuery query) throws Exception;
}
