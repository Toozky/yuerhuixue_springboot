package com.yuerhuixue.dao;

import com.yuerhuixue.general.GeneralDao;
import com.yuerhuixue.pojo.InsType;
import com.yuerhuixue.pojo.InsTypeVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsTypeMapper extends GeneralDao<InsType> {

    //前五条乐器类型
    List<InsType> insTypeListFive();

    //查询所有类型
    List<InsTypeVO> insTypeVOList();

}