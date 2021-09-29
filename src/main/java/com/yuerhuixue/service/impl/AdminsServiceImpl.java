package com.yuerhuixue.service.impl;

import com.yuerhuixue.dao.AdminsMapper;
import com.yuerhuixue.pojo.Admins;
import com.yuerhuixue.pojo.Users;
import com.yuerhuixue.service.AdminsService;
import com.yuerhuixue.utils.MD5Utils;
import com.yuerhuixue.vo.ResultVO;
import com.yuerhuixue.vo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class AdminsServiceImpl implements AdminsService {

    @Autowired
    private AdminsMapper adminsMapper;

    /**
     * 新增管理员
     * @param name 管理员名
     * @param pwd 密码
     * @return 执行结果
     */
    @Override
    public ResultVO adminRegist(String name, String pwd) {

        //1.查询管理员名是否已存在
        Example example = new Example(Admins.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("adminName",name);
        List<Admins> admins = adminsMapper.selectByExample(example);

        //2.如果没有注册，则保存
        if (admins.size() == 0){
            String md5Pwd = MD5Utils.md5(pwd);
            Admins admin = new Admins();
            admin.setAdminName(name);
            admin.setAdminNickname(name);
            admin.setAdminPwd(md5Pwd);
            admin.setAdminImg("headDefault.jpg");
            admin.setCreateTime(new Date());
            admin.setUpdateTime(new Date());
            int i = adminsMapper.insertUseGeneratedKeys(admin);
            if (i > 0){
                return new ResultVO(StatusCode.OK, "添加成功！", admin);
            }else {
                return new ResultVO(StatusCode.NO, "添加失败！", null);
            }
        }else {
            return new ResultVO(StatusCode.NO, "管理员名已存在！", null);
        }
    }

    /**
     * 管理员登录
     * @param name 管理员名
     * @param pwd 密码
     * @return 执行结果
     */
    @Override
    public ResultVO adminLogin(String name, String pwd) {

        //1.查询是否存在该管理员名
        Example example = new Example(Admins.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("adminName",name);
        List<Admins> admins = adminsMapper.selectByExample(example);

        //2.如果存在，判断密码是否正确
        if (admins.size() == 0){
            return new ResultVO(StatusCode.NO, "管理员名不存在！", null);
        }else {
            String md5Pwd = MD5Utils.md5(pwd);
            if (md5Pwd.equals(admins.get(0).getAdminPwd())){
                return new ResultVO(StatusCode.OK, "登陆成功！", admins.get(0));
            }else {
                return new ResultVO(StatusCode.NO, "密码错误！", null);
            }
        }
    }

    /**
     * 信息修改
     * @param admin 管理员对象
     * @return 执行结果
     */
    @Override
    public ResultVO adminModify(Admins admin) {

        //设置修改时间
        admin.setUpdateTime(new Date());

        //根据主键更新字段
        int i = adminsMapper.updateByPrimaryKeySelective(admin);
        if (i > 0){
            return new ResultVO(StatusCode.OK, "修改成功！", admin);
        }else {
            return new ResultVO(StatusCode.NO, "修改失败！", null);
        }
    }

    /**
     * 根据id查询管理员
     * @param adminId 管理员id
     * @return 管理员对象
     */
    @Override
    public Admins findAdminById(Integer adminId) {
        return adminsMapper.selectByPrimaryKey(adminId);
    }

    /**
     * 修改密码
     * @param adminId 管理员id
     * @param pwd 输入密码
     * @param newPwd 修改后密码
     * @return 执行结果
     */
    @Override
    public ResultVO modifyPwd(Integer adminId, String pwd, String newPwd) {

        //获取当前用户密码与输入密码进行比对
        Admins admin = adminsMapper.selectByPrimaryKey(adminId);
        String md5PwdBefore = MD5Utils.md5(pwd);
        if (md5PwdBefore.equals(admin.getAdminPwd())){

            String md5Pwd = MD5Utils.md5(newPwd);
            admin.setAdminPwd(md5Pwd);
            int i = adminsMapper.updateByPrimaryKeySelective(admin);

            if (i > 0){
                return new ResultVO(StatusCode.OK, "修改成功！", null);
            }else {
                return new ResultVO(StatusCode.NO, "修改失败！", null);
            }

        }else {
            return new ResultVO(StatusCode.NO, "输入密码错误！", null);
        }
    }
}
