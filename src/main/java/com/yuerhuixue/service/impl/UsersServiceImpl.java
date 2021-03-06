package com.yuerhuixue.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuerhuixue.dao.UsersMapper;
import com.yuerhuixue.pojo.Users;
import com.yuerhuixue.service.UsersService;
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
import java.util.HashMap;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    /**
     * 用户注册
     * @param name 用户名
     * @param pwd 密码
     * @return 执行结果
     */
    @Override
    public ResultVO userRegist(String name, String pwd) {

        //1.查询用户名是否已被注册
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName",name);
        List<Users> users = usersMapper.selectByExample(example);

        //2.如果没有注册，则保存
        if (users.size() == 0){
            String md5Pwd = MD5Utils.md5(pwd);
            Users user = new Users();
            user.setUserName(name);
            user.setUserNickname(name);
            user.setUserPwd(md5Pwd);
            user.setUserImg("headDefault.jpg");
            user.setUserGender("");
            user.setUserAge("");
            user.setUserEmail("");
            user.setUserTel("");
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            int i = usersMapper.insertUseGeneratedKeys(user);
            if (i > 0){
                return new ResultVO(StatusCode.OK, "注册成功！", user);
            }else {
                return new ResultVO(StatusCode.NO, "注册失败！", null);
            }
        }else {
            return new ResultVO(StatusCode.NO, "用户名已被注册！", null);
        }

    }

    /**
     * 用户登录
     * @param name 用户名
     * @param pwd 密码
     * @return 执行结果
     */
    @Override
    public ResultVO userLogin(String name, String pwd) {

        //1.查询是否存在该用户
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName",name);
        List<Users> users = usersMapper.selectByExample(example);

        //2.如果存在，判断密码是否正确
        if (users.size() == 0){
            return new ResultVO(StatusCode.NO, "用户名不存在！", null);
        }else {
            String md5Pwd = MD5Utils.md5(pwd);
            if (md5Pwd.equals(users.get(0).getUserPwd())){

                //校验密码成功，生成token
                JwtBuilder builder = Jwts.builder();
                String token = builder.setSubject(name)    //token数据
                        .setIssuedAt(new Date())    //token生成时间
                        .setId(users.get(0).getUserId() + "")    //设置用户id为tokenid
                        .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 100))    //设置过期时间
                        .signWith(SignatureAlgorithm.HS256, "yuerhuixue")   //设置加密方式和加密密码
                        .compact();

                //创建map返回用户信息和token
                HashMap<Object, Object> map = new HashMap<>();
                map.put("user", users.get(0));
                map.put("token", token);
                return new ResultVO(StatusCode.OK, "登陆成功！", map);
            }else {
                return new ResultVO(StatusCode.NO, "密码错误！", null);
            }
        }

    }

    /**
     * 信息修改
     * @param user 用户对象
     * @return 执行结果
     */
    @Override
    public ResultVO userModify(Users user) {

        //设置修改时间
        user.setCreateTime(null);
        user.setUpdateTime(new Date());

        //不修改密码
        user.setUserPwd(null);

        int i;

        //查询输入是否与原用户名相同，不同时判断是否重名
        //原用户名
        Users userById = usersMapper.selectByPrimaryKey(user.getUserId());

        //查询输入是否存在
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName",user.getUserName());
        List<Users> users = usersMapper.selectByExample(example);

        //判断
        if (user.getUserName().equals(userById.getUserName())){
            user.setUserName(null);
        }else if (users.size() != 0){
            return new ResultVO(StatusCode.NO, "用户名已存在！", null);
        }

        //根据主键更新字段
        i = usersMapper.updateByPrimaryKeySelective(user);
        if (i > 0){
            user.setUserName(userById.getUserName());
            return new ResultVO(StatusCode.OK, "修改成功！", user);
        }else {
            return new ResultVO(StatusCode.NO, "修改失败！", null);
        }

    }

    /**
     * 根据id查询用户
     * @param userId 用户id
     * @return 用户对象
     */
    @Override
    public ResultVO findUserById(Integer userId) {
        Users user = usersMapper.selectByPrimaryKey(userId);
        return new ResultVO(StatusCode.OK, "查询完成！", user);
    }

    /**
     * 修改密码
     * @param userId 用户id
     * @param pwd 用户输入密码
     * @param newPwd 用户修改后密码
     * @return 执行结果
     */
    @Override
    public ResultVO modifyPwd(Integer userId, String pwd, String newPwd) {

        //获取当前用户密码与输入密码进行比对
        Users user = usersMapper.selectByPrimaryKey(userId);
        String md5PwdBefore = MD5Utils.md5(pwd);
        if (md5PwdBefore.equals(user.getUserPwd())){

            String md5Pwd = MD5Utils.md5(newPwd);
            user.setUserPwd(md5Pwd);
            int i = usersMapper.updateByPrimaryKeySelective(user);

            if (i > 0){
                return new ResultVO(StatusCode.OK, "修改成功！", null);
            }else {
                return new ResultVO(StatusCode.NO, "修改失败！", null);
            }

        }else {
            return new ResultVO(StatusCode.NO, "输入密码错误！", null);
        }
    }

    /**
     * 查询用户列表
     * @param pageNum 页码
     * @param pageSize 当前页码数据条数
     * @return 执行结果
     */
    @Override
    public ResultVO userList(Integer pageNum, Integer pageSize) {
        
        //分页
        PageHelper.startPage(pageNum, pageSize);

        //查询
        List<Users> userList = usersMapper.selectAll();
        PageInfo<Users> userPageInfo = new PageInfo<>(userList);
        return new ResultVO(StatusCode.OK, "查询完成！", userPageInfo);
    }

    /**
     * 管理员修改用户信息
     * @param user 用户对象
     * @return 执行结果
     */
    @Override
    public ResultVO userModifyByAdmin(Users user) {

        //设置修改时间
        user.setUpdateTime(new Date());

        //查询输入是否与原用户名相同，不同时判断是否重名
        //原用户名
        Users userById = usersMapper.selectByPrimaryKey(user.getUserId());

        //查询输入是否存在
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName",user.getUserName());
        List<Users> users = usersMapper.selectByExample(example);

        //判断
        if (user.getUserName().equals(userById.getUserName())){
            user.setUserName(null);
        }else if (users.size() != 0){
            return new ResultVO(StatusCode.NO, "用户名已存在！", null);
        }

        //判断密码是否修改
        if (user.getUserPwd().equals("******")){
            user.setUserPwd(null);
        }else {
            String md5Pwd = MD5Utils.md5(user.getUserPwd());
            user.setUserPwd(md5Pwd);
        }

        //根据主键更新字段
        int i = usersMapper.updateByPrimaryKeySelective(user);
        if (i > 0){
            user.setUserName(userById.getUserName());
            return new ResultVO(StatusCode.OK, "修改成功！", user);
        }else {
            return new ResultVO(StatusCode.NO, "修改失败！", null);
        }
    }

    /**
     * 用户删除
     * @param userId 用户id
     * @return 执行结果
     */
    @Override
    public ResultVO userDelete(Integer userId) {
        int i = usersMapper.deleteByPrimaryKey(userId);

        if(i > 0){
            return new ResultVO(StatusCode.OK, "删除成功！", null);
        }else {
            return new ResultVO(StatusCode.NO, "删除失败！", null);
        }

    }

    /**
     * 用户性别人数统计
     * @return 执行结果
     */
    @Override
    public ResultVO userGenderTotal() {

        HashMap<String, Integer> map = new HashMap<>();
        int maleSize = 0;
        int femaleSize = 0;
        int nullSize = 0;
        List<Users> userList = usersMapper.selectAll();

        //封装统计map
        for (Users user : userList) {
            if (user.getUserGender().equals("male")){
                maleSize++;
            }else if (user.getUserGender().equals("female")){
                femaleSize++;
            }else {
                nullSize++;
            }
            map.put("maleCount",maleSize);
            map.put("femaleCount",femaleSize);
            map.put("nullCount",nullSize);
        }

        return new ResultVO(StatusCode.OK, "统计完成！", map);
    }

    /**
     * 查询用户数
     * @return 执行结果
     */
    @Override
    public ResultVO userTotal() {
        int userTotal = usersMapper.selectAll().size();
        return new ResultVO(StatusCode.OK,"查询成功！", userTotal);
    }
}
