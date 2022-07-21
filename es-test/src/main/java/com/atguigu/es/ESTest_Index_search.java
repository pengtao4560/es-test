package com.atguigu.es;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.GetIndexRequest;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import co.elastic.clients.elasticsearch.indices.IndexState;
import co.elastic.clients.util.DateTime;

import java.io.IOException;

/**
 * 查询索引
 */
public class ESTest_Index_search {

    public static void main(String[] args) throws IOException {
        ElasticsearchClient elasticsearchClient = ESClientUtil.getElasticsearchClient();
        GetIndexRequest.Builder builder = new GetIndexRequest.Builder().index("user");
        GetIndexRequest request = builder.build();
        GetIndexResponse getIndexResponse = elasticsearchClient.indices().get(request);

        IndexState indexState = getIndexResponse.get("user");
        System.out.println(indexState.aliases());
        System.out.println(indexState.mappings());
        System.out.println(indexState.settings());
        DateTime dateTime = indexState.settings().creationDateString();
        System.out.println(dateTime);

    }
}
