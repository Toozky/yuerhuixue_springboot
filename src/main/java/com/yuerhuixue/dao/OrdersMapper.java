package com.yuerhuixue.dao;

import com.yuerhuixue.general.GeneralDao;
import com.yuerhuixue.pojo.Orders;
import com.yuerhuixue.pojo.OrdersVO;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersMapper extends GeneralDao<Orders> {

    //根据id查询完整订单
    OrdersVO findOrderVOById(Integer orderId);

}