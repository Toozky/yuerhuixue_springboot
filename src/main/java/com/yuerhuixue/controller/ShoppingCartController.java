package com.yuerhuixue.controller;

import com.yuerhuixue.pojo.ShoppingCart;
import com.yuerhuixue.service.ShoppingCartService;
import com.yuerhuixue.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shoppingCart")
@Api(value = "购物车操作接口", tags = "购物车管理")
@CrossOrigin
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @ApiOperation("购物车列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int", name = "pageNum", value = "页码", required = true),
            @ApiImplicitParam(dataType = "int", name = "pageSize", value = "当前页码数据条数", required = true),
            @ApiImplicitParam(dataType = "int", name = "userId", value = "用户id", required = true)
    })
    @GetMapping("/list")
    public ResultVO list(@RequestParam("pageNum") Integer pageNum,
                                     @RequestParam("pageSize")Integer pageSize,
                                     @RequestParam("userId") Integer userId){
        return shoppingCartService.shoppingCartList(pageNum, pageSize, userId);
    }

    @ApiOperation("添加购物车接口")
    @PostMapping("/add")
    public ResultVO add(@RequestBody ShoppingCart shoppingCart){
        return shoppingCartService.addShoppingCart(shoppingCart);
    }

    @ApiOperation("修改购物车接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int", name = "cartId", value = "购物车id", required = true),
            @ApiImplicitParam(dataType = "int", name = "cartNumber", value = "乐器数量", required = true)
    })
    @PutMapping("modify")
    public ResultVO modify(@RequestParam("cartId") Integer cartId,
                           @RequestParam("cartNumber") Integer cartNumber){
        return shoppingCartService.modifyShoppingCart(cartId, cartNumber);
    }

    @ApiOperation("删除购物车接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int", name = "cartId", value = "购物车id", required = true)
    })
    @DeleteMapping("delete")
    public ResultVO delete(@RequestParam("cartId") Integer cartId){
        return shoppingCartService.deleteShoppingCart(cartId);
    }

}
