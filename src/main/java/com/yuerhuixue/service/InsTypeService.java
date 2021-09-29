package com.yuerhuixue.service;

import com.yuerhuixue.pojo.InsType;
import com.yuerhuixue.vo.ResultVO;

public interface InsTypeService {

    //增加乐器类型
    ResultVO addInsType(InsType insType);

    //修改乐器类型
    ResultVO modifyInsType(InsType insType);

    //删除乐器类型
    ResultVO deleteInsType(Integer insTypeId);

    //查询所有乐器类型
    ResultVO InsTypeList();

    //根据id查询乐器类型
    InsType findInsTypeById(Integer insTypeId);

    //查询当前类型下所有乐器
    ResultVO insByType(Integer insTypeId);
    
}
