package com.yuerhuixue.service;

import com.yuerhuixue.pojo.Admins;
import com.yuerhuixue.vo.ResultVO;

public interface AdminsService {

    //用户注册
    ResultVO adminRegist(String name, String pwd);

    //用户登录
    ResultVO adminLogin(String name, String pwd);

    //信息修改
    ResultVO adminModify(Admins admin);

    //根据id查询管理员
    Admins findAdminById(Integer adminId);

    //修改密码
    ResultVO modifyPwd(Integer adminId, String pwd, String newPwd);

}
