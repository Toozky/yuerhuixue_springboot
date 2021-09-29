package com.yuerhuixue.dao;

import com.yuerhuixue.general.GeneralDao;
import com.yuerhuixue.pojo.ShoppingCart;
import com.yuerhuixue.pojo.ShoppingCartVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartMapper extends GeneralDao<ShoppingCart> {

    //根据用户id查询购物车
    List<ShoppingCartVO> shoppingCartByUser(Integer userId);

}