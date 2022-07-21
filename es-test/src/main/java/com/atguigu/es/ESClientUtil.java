package com.atguigu.es;


import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

/**
 * ESTest_Client
 * <a href="https://www.elastic.co/guide/en/elasticsearch/client/java-api-client/current/connecting.html">
 *     </a>
 */
public class ESClientUtil {

    public static void main(String[] args) {

        // 创建 ES 客户端

        // Create the low-level client
        ElasticsearchClient elasticsearchClient = getElasticsearchClient();
        elasticsearchClient.shutdown();
        System.out.println("连接成功，已关闭");
    }

    public static ElasticsearchClient getElasticsearchClient() {
        RestClient restClient = RestClient.builder(
                new HttpHost("localhost", 9200)).build();

// Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());
        System.out.println("连接成功");
// And create the API client
        ElasticsearchClient elasticsearchClient = new ElasticsearchClient(transport);
        return elasticsearchClient;
    }
}
