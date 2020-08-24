package com.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "root_index")
public class CloudDiskEntity {
    //id
    @Id
    private String id;
    //名称
    private String name;
    //来源
    private String source;
    //描述
    private String describe;
    //分享时间
    private String shareTime;
    //浏览次数
    private Long browseCount;
    //文件大小
    private Double filesize;
    //分享者
    private String sharePeople;
    //收录时间
    private String collectionTime;
    //网盘地址
    private String address;
}
