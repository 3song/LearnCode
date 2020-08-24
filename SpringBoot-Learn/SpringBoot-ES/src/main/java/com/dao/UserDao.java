package com.dao;

import com.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao  extends ElasticsearchRepository<User,String> {

}
