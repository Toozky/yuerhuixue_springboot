package com.yuerhuixue.service.impl;

import com.yuerhuixue.dao.UserAddrMapper;
import com.yuerhuixue.pojo.UserAddr;
import com.yuerhuixue.service.UserAddrService;
import com.yuerhuixue.vo.ResultVO;
import com.yuerhuixue.vo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class UserAddrServiceImpl implements UserAddrService {

    @Autowired
    private UserAddrMapper userAddrMapper;

    /**
     * 添加收货地址信息
     * @param userAddr 地址信息
     * @return 执行结果
     */
    @Override
    public ResultVO addAddr(UserAddr userAddr) {

        //设置创建和更新时间
        userAddr.setCreateTime(new Date());
        userAddr.setUpdateTime(new Date());

        //是否添加成功
        int i = userAddrMapper.insertUseGeneratedKeys(userAddr);
        if (i > 0){
            return new ResultVO(StatusCode.OK, "添加成功！", userAddr);
        }else {
            return new ResultVO(StatusCode.NO, "添加失败！", null);
        }

    }

    /**
     * 修改收货地址信息
     * @param userAddr 地址信息
     * @return 执行结果
     */
    @Override
    public ResultVO modifyAddr(UserAddr userAddr) {

        //更新修改时间
        userAddr.setCreateTime(null);
        userAddr.setUpdateTime(new Date());

        //是否修改成功
        int i = userAddrMapper.updateByPrimaryKeySelective(userAddr);
        if (i > 0){
            return new ResultVO(StatusCode.OK, "修改成功！", userAddr);
        }else {
            return new ResultVO(StatusCode.NO, "修改失败！", null);
        }

    }

    /**
     * 删除收货地址信息
     * @param userAddrId 地址信息id
     * @return 执行结果
     */
    @Override
    public ResultVO deleteAddr(Integer userAddrId) {

        //是否删除成功
        int i = userAddrMapper.deleteByPrimaryKey(userAddrId);
        if (i > 0) {
            return new ResultVO(StatusCode.OK, "删除成功！", null);
        }else {
            return new ResultVO(StatusCode.OK, "删除失败！", null);
        }

    }

    /**
     * 查询当前用户所有地址信息
     * @param userId 用户id
     * @return 执行结果
     */
    @Override
    public ResultVO addrListByUserId(Integer userId) {

        //根据用户id查询
        Example example = new Example(UserAddr.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);

        List<UserAddr> userAddrs = userAddrMapper.selectByExample(example);
        return new ResultVO(StatusCode.OK, "查询完成！", userAddrs);
    }
}
