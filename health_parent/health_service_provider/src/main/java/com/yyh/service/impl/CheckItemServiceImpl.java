package com.yyh.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yyh.dao.CheckItemDao;
import com.yyh.pojo.CheckItem;
import com.yyh.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 检查项服务
 */

@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemDao checkItemDao;
    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

}
