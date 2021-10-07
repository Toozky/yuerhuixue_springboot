package com.yuerhuixue.service;

import com.yuerhuixue.pojo.Ins;
import com.yuerhuixue.pojo.InsVO;
import com.yuerhuixue.vo.ResultVO;

public interface InsService {

    //增加乐器
    ResultVO addIns(Ins ins);

    //修改乐器
    ResultVO modifyIns(Ins ins);

    //删除乐器
    ResultVO deleteIns(Integer insId);

    //查询所有乐器
    ResultVO insList(Integer pageNum, Integer pageSize);

    //根据id查询乐器
    ResultVO findInsById(Integer insId);

    //根据id查询乐器
    ResultVO findInsVOById(Integer insId);

    //前五条乐器
    ResultVO insListFive();

}
