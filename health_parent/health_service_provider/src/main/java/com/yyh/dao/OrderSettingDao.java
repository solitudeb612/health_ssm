package com.yyh.dao;
import com.yyh.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderSettingDao {
    public void add(OrderSetting orderSetting);
    public void editNumberByOrderDate(OrderSetting orderSetting);
    public long findCountByOrderDate(Date orderDate);
    public List<OrderSetting> getOrderSettingByMonth(Map map);
}
