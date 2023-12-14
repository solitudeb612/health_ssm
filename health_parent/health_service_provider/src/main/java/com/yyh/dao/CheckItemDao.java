package com.yyh.dao;

import com.github.pagehelper.Page;
import com.yyh.pojo.CheckItem;

import java.util.List;

public interface CheckItemDao {
    public void add(CheckItem checkItem);
    public Page<CheckItem> selectByCondition(String queryString);

    void deleteById(Integer id);

    /**
     * 查询该检查项的id,在检查组中出现的次数
     * @param id
     * @return
     */
    public long findCountByCheckItemId(Integer id);

    CheckItem findById(Integer id);

    List<CheckItem> findAll();
    void edit(CheckItem checkItem);
}
