package com.yuerhuixue.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuerhuixue.pojo.Ins;
import com.yuerhuixue.pojo.OrderDetail;
import com.yuerhuixue.pojo.Orders;
import com.yuerhuixue.service.InsService;
import com.yuerhuixue.service.OrdersService;
import com.yuerhuixue.vo.ResultVO;
import com.yuerhuixue.vo.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
@Api(value = "订单操作接口", tags = "订单管理")
@CrossOrigin
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private InsService insService;

    @ApiOperation("订单列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int", name = "pageNum", value = "页码", required = true),
            @ApiImplicitParam(dataType = "int", name = "pageSize", value = "当前页码数据条数", required = true),
            @ApiImplicitParam(dataType = "int", name = "userId", value = "用户id", required = true)
    })
    @GetMapping("/list")
    public ResultVO list(@RequestParam("pageNum") Integer pageNum,
                         @RequestParam("pageSize")Integer pageSize,
                         @RequestParam("userId") Integer userId){
        return ordersService.findOrderVOByUserId(pageNum, pageSize, userId);
    }

    @ApiOperation("添加订单接口")
    @PostMapping("/addOrder")
    public ResultVO addOrder(@RequestBody Orders order){
        return ordersService.addOrder(order);
    }

    @ApiOperation("添加订单细节接口")
    @PostMapping("/addOrderDetail")
    public ResultVO addOrderDetail(String orderDetailListToString) {

        List<OrderDetail> orderDetailList = JSONArray.parseArray(orderDetailListToString, OrderDetail.class);

        try {
            for (OrderDetail orderDetail : orderDetailList) {
                ordersService.addOrderDetail(orderDetail);

                //获取乐器原始库存
                ResultVO resultVO = insService.findInsById(Integer.parseInt(orderDetail.getInsId()));
                Ins insById = (Ins)resultVO.getData();
                Integer insStockBefore = insById.getInsStock();

                //获取已购买数量
                Integer buyNumber = orderDetail.getBuyNumber();

                //待修改的数据对象
                Ins ins = new Ins();
                ins.setInsId(Integer.parseInt(orderDetail.getInsId()));

                //修改
                if (insStockBefore >= buyNumber){
                    Integer insStock = insStockBefore - buyNumber;
                    ins.setInsStock(insStock);
                    insService.modifyIns(ins);
                }else {
                    ordersService.deleteOrder(orderDetail.getOrderId());
                    return new ResultVO(StatusCode.NO, orderDetail.getInsName()+"库存不足！", null);
                }

            }
            return new ResultVO(StatusCode.OK, "添加成功！", null);
        }catch (Exception e){
            System.out.println(e);
            ordersService.deleteOrder(orderDetailList.get(0).getOrderId());
            return new ResultVO(StatusCode.NO, "添加失败！", null);
        }
    }

}
