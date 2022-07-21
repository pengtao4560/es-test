package com.atguigu.es;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;

import java.io.IOException;

/**
 *
 */
public class ESTest_Index_Create {

    public static void main(String[] args) throws IOException {
        ElasticsearchClient elasticsearchClient = ESClientUtil.getElasticsearchClient();
        // 创建索引
        CreateIndexRequest.Builder user = new CreateIndexRequest.Builder().index("pengtao");

        CreateIndexResponse createIndexResponse = elasticsearchClient.indices().create(user.build());

        // 响应状态
        boolean acknowledged = createIndexResponse.acknowledged();
        System.out.println("索引操作：" + acknowledged);
        System.out.println("createIndexResponse：" + createIndexResponse);
    }
}
