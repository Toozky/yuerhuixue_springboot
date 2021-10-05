package com.yuerhuixue.service.impl;

import com.yuerhuixue.dao.OrdersMapper;
import com.yuerhuixue.pojo.OrdersVO;
import com.yuerhuixue.service.OrdersService;
import com.yuerhuixue.vo.ResultVO;
import com.yuerhuixue.vo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;


    @Override
    public ResultVO findOrderVOByUserId(Integer userId) {
        List<OrdersVO> orderVOs = ordersMapper.findOrderVOByUserId(userId);
        return new ResultVO(StatusCode.OK,"查询完成！", orderVOs);
    }

}
