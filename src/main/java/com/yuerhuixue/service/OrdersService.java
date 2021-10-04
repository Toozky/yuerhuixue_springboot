package com.yuerhuixue.service;

import com.yuerhuixue.vo.ResultVO;

public interface OrdersService {

    //查询当前用户的所有订单
    ResultVO findOrderVOByUserId(Integer userId);

}
