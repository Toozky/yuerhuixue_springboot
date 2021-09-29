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
@ResponseBody
@CrossOrigin
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @ApiOperation("购物车列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "integer", name = "userId", value = "用户id", required = true)
    })
    @GetMapping("/list")
    public ResultVO shoppingCartList(@RequestParam("userId") Integer userId){
        return shoppingCartService.shoppingCartList(userId);
    }

    @ApiOperation("添加购物车接口")
    @PostMapping("/add")
    public ResultVO addShoppingCart(@RequestBody ShoppingCart shoppingCart){
        return shoppingCartService.addShoppingCart(shoppingCart);
    }

    @ApiOperation("修改购物车接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "integer", name = "cartId", value = "购物车id", required = true),
            @ApiImplicitParam(dataType = "integer", name = "cartNumber", value = "乐器数量", required = true)
    })
    @PutMapping("modify")
    public ResultVO modifyShoppingCart(@RequestParam("cartId") Integer cartId,
                                       @RequestParam("cartNumber") Integer cartNumber){
        return shoppingCartService.modifyShoppingCart(cartId, cartNumber);
    }

    @ApiOperation("删除购物车接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "integer", name = "cartId", value = "购物车id", required = true)
    })
    @DeleteMapping("delete")
    public ResultVO deleteShoppingCart(@RequestParam("cartId") Integer cartId){
        return shoppingCartService.deleteShoppingCart(cartId);
    }

}
