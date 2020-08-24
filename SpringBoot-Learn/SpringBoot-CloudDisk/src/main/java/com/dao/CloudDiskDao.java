package com.dao;

import com.entity.CloudDiskEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CloudDiskDao extends ElasticsearchRepository<CloudDiskEntity,String> {

}
