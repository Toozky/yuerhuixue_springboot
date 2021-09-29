package com.yuerhuixue.service.impl;

import com.yuerhuixue.dao.InsMapper;
import com.yuerhuixue.pojo.Ins;
import com.yuerhuixue.pojo.InsVO;
import com.yuerhuixue.service.InsService;
import com.yuerhuixue.vo.ResultVO;
import com.yuerhuixue.vo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InsServiceImpl implements InsService {

    @Autowired
    private InsMapper insMapper;

    /**
     * 添加乐器信息
     * @param ins 乐器信息
     * @return 执行结果
     */
    @Override
    public ResultVO addIns(Ins ins) {

        //设置创建和更新时间
        ins.setCreateTime(new Date());
        ins.setUpdateTime(new Date());

        //如果图片为空，则默认
        if (ins.getInsImg() == null){
            ins.setInsImg("occupancy.jpg");
        }

        //是否添加成功
        int i = insMapper.insertUseGeneratedKeys(ins);
        if (i > 0){
            return new ResultVO(StatusCode.OK, "添加成功！", ins);
        }else {
            return new ResultVO(StatusCode.NO, "添加失败！", null);
        }
    }

    /**
     * 修改乐器信息
     * @param ins 乐器信息
     * @return 执行结果
     */
    @Override
    public ResultVO modifyIns(Ins ins) {

        //更新修改时间
        ins.setUpdateTime(new Date());

        //是否修改成功
        int i = insMapper.updateByPrimaryKeySelective(ins);
        if (i > 0){
            return new ResultVO(StatusCode.OK, "修改成功！", ins);
        }else {
            return new ResultVO(StatusCode.NO, "修改失败！", null);
        }
    }

    /**
     * 删除乐器信息
     * @param insId 乐器id
     * @return 执行结果
     */
    @Override
    public ResultVO deleteIns(Integer insId) {

        //是否删除成功
        int i = insMapper.deleteByPrimaryKey(insId);
        if (i > 0) {
            return new ResultVO(StatusCode.OK, "删除成功！", null);
        }else {
            return new ResultVO(StatusCode.OK, "删除失败！", null);
        }
    }

    /**
     * 查询所有乐器
     * @return 执行结果
     */
    @Override
    public ResultVO InsList() {

        //查询所有
        List<Ins> insList = insMapper.selectAll();
        return new ResultVO(StatusCode.OK, "查询完成！", insList);
    }

    /**
     * 根据id查询乐器
     * @param insId 乐器id
     * @return 乐器对象
     */
    @Override
    public Ins findInsById(Integer insId) {
        return insMapper.selectByPrimaryKey(insId);
    }

    /**
     * 根据id查询乐器
     * @param insId 乐器id
     * @return 乐器对象
     */
    @Override
    public InsVO findInsVOById(Integer insId) {
        return insMapper.findInsVOById(insId);
    }
}
