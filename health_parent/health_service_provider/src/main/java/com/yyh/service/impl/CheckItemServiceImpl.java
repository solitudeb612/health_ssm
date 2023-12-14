package com.yyh.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yyh.dao.CheckItemDao;
import com.yyh.entity.PageResult;
import com.yyh.entity.QueryPageBean;
import com.yyh.pojo.CheckItem;
import com.yyh.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 检查项服务
 */

@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemDao checkItemDao;

    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }

    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        //完成分页查询，基于mybatis框架提供的分页助手完成
        PageHelper.startPage(currentPage, pageSize);//告诉MyBatis在接下来的查询中应用分页规则,底层是通过线程绑定参数，将分页参数传入到查询的sql中
        Page<CheckItem> page = checkItemDao.selectByCondition(queryString);
        long total = page.getTotal();
        List<CheckItem> result = page.getResult();
        //返回的是自己定义的PageResult , 而不是Mybatis的Page,因为我在自己定义的PageResult实现了Serializable接口，但是PageHelper提供的Page没有实现
        return new PageResult(total, result);
    }

    @Override
    public void deleteById(Integer id) {
        long count = checkItemDao.findCountByCheckItemId(id);
        if(count > 0){
            //当前检查项已经被关联到检查组，不允许删除
            new RuntimeException(); //TODO
        }
        checkItemDao.deleteById(id);
    }

    @Override
    public CheckItem findById(Integer id) {
        return checkItemDao.findById(id);
    }

    @Override
    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
    }


}
