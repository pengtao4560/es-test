package com.atguigu.es;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.DeleteIndexRequest;
import co.elastic.clients.elasticsearch.indices.DeleteIndexResponse;

import java.io.IOException;

/**
 *
 */
public class ESTest_Index_Delete {

    public static void main(String[] args) throws IOException {
        ElasticsearchClient elasticsearchClient = ESClientUtil.getElasticsearchClient();
        // 创建索引
        DeleteIndexRequest.Builder user = new DeleteIndexRequest.Builder().index("pengtao");

        DeleteIndexResponse deleteIndexResponse = elasticsearchClient.indices().delete(user.build());

        // 响应状态
        boolean acknowledged = deleteIndexResponse.acknowledged();
        System.out.println("索引操作：" + acknowledged);
        System.out.println("deleteIndexResponse：" + deleteIndexResponse);
    }
}
