package com.atguigu.es;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.Result;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 *
 */
public class ESTest_Doc_Insert {

    public static void main(String[] args) throws IOException {
        ElasticsearchClient elasticsearchClient = ESClientUtil.getElasticsearchClient();
        IndexRequest.Builder userBuilder = new IndexRequest.Builder().index("user");
        userBuilder.id("1001");
        User user = new User();
        user.setName("pengtao");
        user.setAge(30);
        user.setSex("男");
        userBuilder.document(user);

        IndexRequest request = userBuilder.build();

        //向ES插入数据，必须将数据转换位JS0W格式
        ObjectMapper mapper = new ObjectMapper();
        // String userStr = mapper.writeValueAsString(user);


        IndexResponse response = elasticsearchClient.index(request);
        Result result = response.result();
        System.out.println(result);

        elasticsearchClient.shutdown();

    }
}
