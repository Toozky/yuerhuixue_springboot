package com.yuerhuixue.controller;

import com.yuerhuixue.pojo.OrderDetail;
import com.yuerhuixue.pojo.Orders;
import com.yuerhuixue.service.OrdersService;
import com.yuerhuixue.vo.ResultVO;
import com.yuerhuixue.vo.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@Api(value = "订单操作接口", tags = "订单管理")
@CrossOrigin
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @ApiOperation("添加订单接口")
    @PostMapping("/addOrder")
    public ResultVO addOrder(@RequestBody Orders order){
        return ordersService.addOrder(order);
    }

    @ApiOperation("添加订单细节接口")
    @PostMapping("/addOrderDetail")
    public ResultVO addOrderDetail(@RequestBody List<OrderDetail> orderDetailList){
        try {
            for (OrderDetail orderDetail : orderDetailList) {
                ordersService.addOrderDetail(orderDetail);
            }
            return new ResultVO(StatusCode.OK, "添加成功！", null);
        }catch (Exception e){
            return new ResultVO(StatusCode.NO, "添加失败！", null);
        }
    }

}
