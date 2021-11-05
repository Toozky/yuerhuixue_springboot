package com.yuerhuixue.service;

import com.yuerhuixue.pojo.ShoppingCart;
import com.yuerhuixue.vo.ResultVO;

public interface ShoppingCartService {

    //购物车列表
    ResultVO shoppingCartList(Integer pageNum, Integer pageSize, Integer userId);

    //添加购物车乐器
    ResultVO addShoppingCart(ShoppingCart shoppingCart);

    //修改购物车乐器
    ResultVO modifyShoppingCart(Integer cartId, Integer cartNumber);

    //删除购物车乐器
    ResultVO deleteShoppingCart(Integer cartId);

}
