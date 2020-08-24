package com.controller;

import com.dao.CloudDiskDao;
import com.entity.CloudDiskEntity;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PageController {
    @Resource
    private CloudDiskDao cloudDiskDao;
    //查询按条件查询  带分页
    // page 从零开始  表示当前页数
    // value 表示页面大小 PageableDefault
    @RequestMapping("/page")
    public String search(String keyWord, @PageableDefault(page = 0,value = 2) Pageable pageable, HttpServletRequest request){
        Long startTime=System.currentTimeMillis();
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
        request.setAttribute("page", diskEntities);
        //高亮字体
        request.setAttribute("keyWord", keyWord);
        //查询总数
        long total=diskEntities.getTotalElements();
        request.setAttribute("total", total);
        //计算分页数
        int totalPage=(int) ((total-1)/pageable.getPageSize()+1);
        request.setAttribute("totalPage", totalPage);
        // 程序运行时间
        Long endTime=System.currentTimeMillis();
        request.setAttribute("time", endTime-startTime);
        return "index";
    }
}
