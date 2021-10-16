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
    ResultVO insTypeList();

    //根据id查询乐器类型
    ResultVO findInsTypeById(Integer insTypeId);

    //查询当前类型下所有乐器
    ResultVO insByType(Integer insTypeId, Integer pageNum, Integer pageSize);

    //前五条乐器类型
    ResultVO insTypeListFive();

    //查询所有乐器类型（分页）
    ResultVO insTypePageList(Integer pageNum, Integer pageSize);
    
}
