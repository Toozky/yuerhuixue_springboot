package com.yuerhuixue.service.impl;

import com.yuerhuixue.dao.InsMapper;
import com.yuerhuixue.dao.InsTypeMapper;
import com.yuerhuixue.pojo.Ins;
import com.yuerhuixue.pojo.InsType;
import com.yuerhuixue.service.InsTypeService;
import com.yuerhuixue.vo.ResultVO;
import com.yuerhuixue.vo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class InsTypeServiceImpl implements InsTypeService {
    
    @Autowired
    private InsTypeMapper insTypeMapper;
    
    @Autowired
    private InsMapper insMapper;

    /**
     * 添加乐器类型信息
     * @param insType 乐器类型信息
     * @return 执行结果
     */
    @Override
    public ResultVO addInsType(InsType insType) {

        //设置创建和更新时间
        insType.setCreateTime(new Date());
        insType.setUpdateTime(new Date());


        //如果图片为空，则默认
        if (insType.getTypeImg() == null){
            insType.setTypeImg("occupancy.jpg");
        }

        //是否添加成功
        int i = insTypeMapper.insertUseGeneratedKeys(insType);
        if (i > 0){
            return new ResultVO(StatusCode.OK, "添加成功！", insType);
        }else {
            return new ResultVO(StatusCode.NO, "添加失败！", null);
        }
    }

    /**
     * 修改乐器类型信息
     * @param insType 乐器类型信息
     * @return 执行结果
     */
    @Override
    public ResultVO modifyInsType(InsType insType) {

        //更新修改时间
        insType.setUpdateTime(new Date());

        //是否修改成功
        int i = insTypeMapper.updateByPrimaryKeySelective(insType);
        if (i > 0){
            return new ResultVO(StatusCode.OK, "修改成功！", insType);
        }else {
            return new ResultVO(StatusCode.NO, "修改失败！", null);
        }
    }

    /**
     * 删除乐器类型信息
     * @param insTypeId 乐器id
     * @return 执行结果
     */
    @Override
    public ResultVO deleteInsType(Integer insTypeId) {

        //是否删除成功
        int i = insTypeMapper.deleteByPrimaryKey(insTypeId);
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
    public ResultVO insTypeList() {
        List<InsType> insTypeList = insTypeMapper.selectAll();
        return new ResultVO(StatusCode.OK, "查询完成！", insTypeList);
    }

    /**
     * 根据id查询乐器类型
     * @param insTypeId 乐器类型id
     * @return 乐器类型对象
     */
    @Override
    public ResultVO findInsTypeById(Integer insTypeId) {
        InsType insType = insTypeMapper.selectByPrimaryKey(insTypeId);
        return new ResultVO(StatusCode.OK, "查询完成！", insType);
    }

    /**
     * 根据id查询当前类型乐器
     * @param insTypeId 乐器类型id
     * @return 执行结果
     */
    @Override
    public ResultVO insByType(Integer insTypeId) {
        
        //根据id查询当前类型视频
        Example example = new Example(Ins.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("typeId", insTypeId);
        List<Ins> insList = insMapper.selectByExample(example);

        if (insList.size() == 0){
            return new ResultVO(StatusCode.NO,"该分类下无乐器！",null);
        }else {
            return new ResultVO(StatusCode.OK, "查询成功！", insList);
        }
    }

    /**
     * 查询前五条数据
     * @return 执行结果
     */
    @Override
    public ResultVO insTypeListFive() {
        List<InsType> insTypeListFive = insTypeMapper.insTypeListFive();
        return new ResultVO(StatusCode.OK, "查询完成！", insTypeListFive);
    }

}
