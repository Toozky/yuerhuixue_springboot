package com.yuerhuixue.service;

import com.yuerhuixue.pojo.UserAddr;
import com.yuerhuixue.vo.ResultVO;

public interface UserAddrService {

    //添加收货地址信息
    ResultVO addAddr(UserAddr userAddr);

    //修改收货地址信息
    ResultVO modifyAddr(UserAddr userAddr);

    //删除收货地址信息
    ResultVO deleteAddr(Integer userAddrId);

    //查询用户所有地址
    ResultVO addrListByUserId(Integer pageNum, Integer pageSize, Integer userId);

}
