package com.controller;

import com.dao.CloudDiskDao;
import com.entity.CloudDiskEntity;
import com.google.common.collect.Lists;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

// ES整合SpringBoot增删改查
@RestController
public class CloudDiskController {
    @Resource
    private CloudDiskDao cloudDiskDao;

    // RestFul风格方法 根据id查询
    @RequestMapping("/findById/{id}")
    public Optional<CloudDiskEntity> findById(@PathVariable String id){
         return cloudDiskDao.findById(id);
    }
    //查询按条件查询  带分页
    // page 从零开始  表示当前页数
    // value 表示页面大小 PageableDefault
    @RequestMapping("/search")
    public List<CloudDiskEntity> search(String keyWord,@PageableDefault(page = 0,value = 2) Pageable pageable){
        BoolQueryBuilder queryBool= QueryBuilders.boolQuery();
        if (!StringUtils.isEmpty(keyWord)){
            QueryBuilders.matchQuery("name", keyWord);//根据name模糊查询
            queryBool.must();
        }
//        queryBool.must(
//                QueryBuilders.boolQuery()
//                        .should(QueryBuilders.matchQuery("xm","好的"))//分词后匹配
//                        .should(QueryBuilders.matchPhraseQuery("addr","钱江路"))//匹配完整词
//                        .should(QueryBuilders.termQuery("status",0))//完全匹配
//                        // .should(QueryBuilders.termsQuery("keyword",string[]))//多关键字匹配
//);

//        Iterable<CloudDiskEntity> diskEntities =cloudDiskDao.search(queryBool);不带分页
        Page<CloudDiskEntity> diskEntities = cloudDiskDao.search(queryBool, pageable); //带分页 写死0 ，2
        return Lists.newArrayList(diskEntities);
    }
}
