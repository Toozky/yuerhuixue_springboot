package com.yuerhuixue.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuerhuixue.dao.OrderDetailMapper;
import com.yuerhuixue.dao.OrdersMapper;
import com.yuerhuixue.pojo.OrderDetail;
import com.yuerhuixue.pojo.Orders;
import com.yuerhuixue.pojo.OrdersVO;
import com.yuerhuixue.service.OrdersService;
import com.yuerhuixue.vo.ResultVO;
import com.yuerhuixue.vo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    /**
     * 查询当前用户的所有订单
     * @param userId 用户id
     * @return 执行结果
     */
    @Override
    public ResultVO findOrderVOByUserId(Integer pageNum, Integer pageSize, Integer userId) {

        //分页
        PageHelper.startPage(pageNum, pageSize);

        //查询订单
        List<OrdersVO> orderVOList = ordersMapper.findOrderVOByUserId(userId);
        PageInfo<OrdersVO> ordersVOPageInfo = new PageInfo<>(orderVOList);
        return new ResultVO(StatusCode.OK,"查询完成！", ordersVOPageInfo);
    }

    @Override
    public ResultVO addOrder(Orders order) {
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        int insert = ordersMapper.insertUseGeneratedKeys(order);
        if (insert > 0){
            return new ResultVO(StatusCode.OK,"购买完成！", order.getOrderId());
        }else {
            return new ResultVO(StatusCode.NO,"购买失败！", null);
        }
    }

    @Override
    public ResultVO addOrderDetail(OrderDetail orderDetail) {
        int insert = orderDetailMapper.insertUseGeneratedKeys(orderDetail);
        if (insert > 0){
            return new ResultVO(StatusCode.OK,"购买完成！", null);
        }else {
            return new ResultVO(StatusCode.NO,"购买失败！", null);
        }
    }
}
