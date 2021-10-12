package com.yuerhuixue.service.impl;

import com.yuerhuixue.dao.AdminsMapper;
import com.yuerhuixue.pojo.Admins;
import com.yuerhuixue.service.AdminsService;
import com.yuerhuixue.utils.MD5Utils;
import com.yuerhuixue.vo.ResultVO;
import com.yuerhuixue.vo.StatusCode;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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

                //校验密码成功，生成token
                JwtBuilder builder = Jwts.builder();
                String token = builder.setSubject(name)    //token数据
                        .setIssuedAt(new Date())    //token生成时间
                        .setId(admins.get(0).getAdminId() + "")    //设置管理员id为tokenid
                        .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 100))    //设置过期时间
                        .signWith(SignatureAlgorithm.HS256, "yuerhuixue")   //设置加密方式和加密密码
                        .compact();

                return new ResultVO(StatusCode.OK, token, admins.get(0));
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

        //不修改密码
        admin.setAdminPwd(null);

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
    public ResultVO findAdminById(Integer adminId) {
        Admins admin = adminsMapper.selectByPrimaryKey(adminId);
        return new ResultVO(StatusCode.OK, "查询完成！", admin);
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
