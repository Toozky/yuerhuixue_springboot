package com.yuerhuixue.dao;

import com.yuerhuixue.general.GeneralDao;
import com.yuerhuixue.pojo.Ins;
import com.yuerhuixue.pojo.InsVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsMapper extends GeneralDao<Ins> {

    //根据id查询乐器
    InsVO findInsVOById(Integer insId);

}