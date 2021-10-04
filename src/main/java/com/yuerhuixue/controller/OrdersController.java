package com.yuerhuixue.controller;

import com.yuerhuixue.service.OrdersService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Api(value = "订单操作接口", tags = "订单管理")
@ResponseBody
@CrossOrigin
public class OrdersController {

    @Autowired
    private OrdersService ordersService;


}
