package com.yyh.service;


import com.yyh.entity.PageResult;
import com.yyh.entity.QueryPageBean;
import com.yyh.pojo.Setmeal;

import java.util.List;

public interface SetmealService {
    public void add(Setmeal setmeal, Integer[] checkgroupIds);
    public PageResult pageQuery(QueryPageBean queryPageBean);
    List<Setmeal> findAll();
    Setmeal findById(int id);
}
