package com.yuerhuixue.service;

import com.yuerhuixue.pojo.Users;
import com.yuerhuixue.vo.ResultVO;

public interface UsersService {

    //用户注册
    ResultVO userRegist(String name, String pwd);

    //用户登录
    ResultVO userLogin(String name, String pwd);

    //用户信息修改
    ResultVO userModify(Users user);

    //根据id查询用户
    ResultVO findUserById(Integer userId);

    //修改密码
    ResultVO modifyPwd(Integer userId, String pwd, String NewPwd);

    //用户列表
    ResultVO userList(Integer pageNum, Integer pageSize);


}
