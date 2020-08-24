package com.config;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EsConfig {
    @Bean
    public JSONObject jsonObject(){
        return new JSONObject();
    }

    @Bean
    public RestHighLevelClient client(){
        RestHighLevelClient client=new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.200.3",9200,"http"),
                        new HttpHost("192.168.200.4",9200,"http"),
                        new HttpHost("192.168.200.5",9200,"http")
                        //new HttpHost("localhost",9200,"http")
                        //如果有其他节点，在这里添加即可
                )
        );
        return client;
    }
}
