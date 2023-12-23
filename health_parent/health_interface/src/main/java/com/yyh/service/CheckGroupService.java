package com.yyh.service;



import com.yyh.entity.PageResult;
import com.yyh.entity.QueryPageBean;
import com.yyh.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {
    public void add(CheckGroup checkGroup, Integer[] checkitemIds);

    PageResult pageQuery(QueryPageBean queryPageBean);

    CheckGroup findById(Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);


    void edit(CheckGroup checkGroup, Integer[] checkitemIds);
}
