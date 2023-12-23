package com.yyh.dao;

import com.github.pagehelper.Page;
import com.yyh.pojo.Setmeal;

import java.util.Map;

public interface SetmealDao {
    public void add(Setmeal setmeal);
    public void setSetmealAndCheckGroup(Map map);
    public Page<Setmeal> findByCondition(String queryString);
}
