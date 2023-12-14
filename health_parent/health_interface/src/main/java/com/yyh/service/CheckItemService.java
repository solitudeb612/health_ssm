package com.yyh.service;

import com.yyh.entity.PageResult;
import com.yyh.entity.QueryPageBean;
import com.yyh.pojo.CheckItem;

import java.util.List;

/**
 *检查项服务接口
 */
public interface CheckItemService {
    List<CheckItem> findAll();

    public void add(CheckItem checkItem);

    public PageResult pageQuery(QueryPageBean queryPageBean);

    void deleteById(Integer id);

    public CheckItem findById(Integer id);

    void edit(CheckItem checkItem);

}
