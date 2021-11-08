package com.yuerhuixue.service;

import com.yuerhuixue.pojo.OrderDetail;
import com.yuerhuixue.pojo.Orders;
import com.yuerhuixue.vo.ResultVO;

public interface OrdersService {

    //查询当前用户的所有订单
    ResultVO findOrderVOByUserId(Integer pageNum, Integer pageSize, Integer userId);

    //添加订单
    ResultVO addOrder(Orders order);

    //添加订单细节
    ResultVO addOrderDetail(OrderDetail orderDetail);

    //删除订单
    ResultVO deleteOrder(Integer id);

}
