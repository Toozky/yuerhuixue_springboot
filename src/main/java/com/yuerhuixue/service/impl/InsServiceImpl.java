package com.yuerhuixue.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuerhuixue.dao.InsMapper;
import com.yuerhuixue.pojo.Ins;
import com.yuerhuixue.pojo.InsVO;
import com.yuerhuixue.service.InsService;
import com.yuerhuixue.vo.ResultVO;
import com.yuerhuixue.vo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
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
     * 查询乐器列表
     * @param pageNum 页码
     * @param pageSize 当前页码数据条数
     * @return 执行结果
     */
    @Override
    public ResultVO insList(Integer pageNum, Integer pageSize) {

        //分页
        PageHelper.startPage(pageNum, pageSize);

        //查询
        List<Ins> insList = insMapper.selectAll();
        PageInfo<Ins> insPageInfo = new PageInfo<>(insList);
        return new ResultVO(StatusCode.OK, "查询完成！", insPageInfo);
    }

    /**
     * 根据id查询乐器
     * @param insId 乐器id
     * @return 乐器对象
     */
    @Override
    public ResultVO findInsById(Integer insId) {
        Ins ins = insMapper.selectByPrimaryKey(insId);
        return new ResultVO(StatusCode.OK, "查询完成！", ins);
    }

    /**
     * 根据id查询乐器
     * @param insId 乐器id
     * @return 乐器对象
     */
    @Override
    public ResultVO findInsVOById(Integer insId) {
        InsVO insVO = insMapper.findInsVOById(insId);
        return new ResultVO(StatusCode.OK, "查询完成！", insVO);
    }

    /**
     * 查询前五条数据
     * @return 执行结果
     */
    @Override
    public ResultVO insListFive() {
        List<Ins> insListFive = insMapper.insListFive();
        return new ResultVO(StatusCode.OK, "查询完成！", insListFive);
    }

    /**
     * 查询最贵的乐器
     * @return
     */
    @Override
    public ResultVO mostExpensive() {
        List<Ins> insList = insMapper.selectAll();

        //记录金额最高的金额以及对应乐器id
        BigDecimal insPrice = new BigDecimal(0);
        String insName = null;

        //map存储
        HashMap<Object, Object> map = new HashMap<>();

        for (Ins ins : insList) {
            if (ins.getInsPrice().compareTo(insPrice) > 0){
                insPrice = ins.getInsPrice();
                insName = ins.getInsName();
            }
        }

        map.put("insName", insName);
        map.put("insPrice", insPrice);
        return new ResultVO(StatusCode.OK, "查询完成！", map);

    }

}
